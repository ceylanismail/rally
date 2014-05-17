volley-core-adapter
---

At first, special thanks to [merihakar](https://github.com/merihakar) for his contributions!

*volley-core-adapter* is a simple adapter between Google's volley and your Android network structure.
It simply aims to reduce the amount of code written for the base network structure.

Setup
---
*for gradle users: ([here](http://tools.android.com/tech-docs/new-build-system) is more detail about gradle build system)*

* clone the project under "/libraries" folder of your root project
* add these lines into your `settings.gradle` file:

```
include 'core-app'
project (':core-app').projectDir = new File('libraries/volley-core-adapter/core-app')
include 'volley'
project (':volley').projectDir = new File('libraries/volley-core-adapter/core-libraries/volley')
```
 * to declare the dependencies, add these lines into your `build.gradle` file:

```
dependencies {

...

compile project(':core-app')
compile project(':volley')

...
}
```

That's all! Now it's ready to be used.

Usage - What do you need?
---
*I'll explain here how I use this, but you can easily implement your own way too!*

* Extend your Android Application class to `CoreBaseApplication` for declaring and initialising `volley` instances.

(This could be done in another place too, but it's just easier in here)

* Create a `BaseRequest` class that extends `CoreBaseRequest`.
(Don't forget! This whole library is all about more modular and reusable network structure.

Here is an example of simple *BaseRequest* class:

```
public abstract class BaseRequest extends CoreBaseRequest {

    @Override
    protected String baseUrl() {
        return <YOUR_BASE_URL>;
    }

    /**
     * @return content type of the request
     */
    protected String contentType() {
        return httpMethod() == Request.Method.GET ? "" : "application/json";
    }

    /**
     * @return key value map of headers required for the request
     */
    protected Map<String, String> getHeaders() {
        // Add your headers here, if you need
    }
}

```
***
And here is an example of simple *POST* request:

```
public class CreateUserRequest extends BaseRequest {

    private final <YourUserClass> mUser;

    public CreateUserRequest(Context context, CustomUser user) {
        super(context);
        mUser = user;
    }

    @Override
    protected int httpMethod() {
        return Request.Method.POST;
    }

    @Override
    protected String path() {
        return <END_POINT_OF_THE_ACTION>";
    }

    @Override
    protected Class responseClass() {
        return <MODEL_CLASS_OF_YOUR_RESPONSE>;
    }

    @Override
    protected String body() {
        return new GsonBuilder().create().toJson(mUser);
    }
}

```
***

Usage - How to call?
---

* EASY!
```
<YOUR_APPLICATION_CLASS>.getInstance().getRequestQueue().add(new CreateUserRequest(getActivity(), mUser) {
                @Override
                protected void onSuccess(Object response) {
                    super.onSuccess(response);
                    UserInfo userInfo = (UserInfo) response;

                    // handle your response here
                }

                @Override
                protected void onError(VolleyError error) {
                    super.onError(error);

                    // handle your error here
                }
            }.create());
```

`<MODEL_CLASS_OF_YOUR_RESPONSE>` is a response class to retrieve the network response, and <YourUserClass> is the request class to send the request to the server, in a simple way.
Hence you don't have to worry anything about parsing, `gson` handles it in an elegant way.


#####Some other useful methods:
* `CoreVolleyUtil.getMethodName`

Returns the `String` value of the HTTP Methods. It might be necessary if you use HTTP verbs as parameters,
(i.e Authorising your calls, since `volley` has `int` implementation of them.)

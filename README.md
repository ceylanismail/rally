volley-core-adapter
---
*volley-core-adapter* is a simple adapter that implements very basic functionalities for the upper level of your Android Network code.
It is aimed to reduce the amount of code that has to be written each time for a new project.

Setup
---
*Explanation is for gradle users:*

* clone the project under "/libraries" folder of your root project ([here](http://tools.android.com/tech-docs/new-build-system) is more detail about gradle build system)
* add these lines in your `settings.gradle` file:

```
include 'core-app'
project (':core-app').projectDir = new File('libraries/volley-core-adapter/core-app')
include 'volley'
project (':volley').projectDir = new File('libraries/volley-core-adapter/core-libraries/volley')
```
 * to declare dependency, add these ones to your `build.gradle` file:

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

* Extend your Android Application class to `CoreBaseApplication` for declaring and initializing `volley` instances. (That could be done in another place too, but it's just easier in here)
* Create a `BaseRequest` class that extends `CoreBaseRequest`. Simple example:

(Don't forget! This whole project is all about more modular network structure. So, please write your own "Base" classes)

```
public abstract class BaseRequest extends CoreBaseRequest {

    @Override
    protected String baseUrl() {
        return <BASE_URL>;
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
And here is simple POST request:

```
public class CreateUserRequest extends BaseRequest {

    private final CustomUser mUser;

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
        return <PATH>";
    }

    @Override
    protected Class responseClass() {
        return UserInfo.class;
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

                    // continue what you need to do here
                }

                @Override
                protected void onError(VolleyError error) {
                    super.onError(error);

                    // handle your error here
                }
            }.create());
```

`UserInfo` and `User` classes are examples of passing the objects as parameters. Therefor you don't have to worry anything about parsing, `gson` handles it in a beautiful way with `volley`.

Special thanks to [@merihakar](https://github.com/merihakar) for his contributions!

#####Some other useful methods:
* `CoreVolleyUtil.getMethodName`

Returns the `String` value of the HTTP Methods. It might be necessary if you use HTTP verbs as parameters, i.e authorizing your calls, since `volley` has `int` implementation of them.

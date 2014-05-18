# rally

*rally* is a simple wrapper around Google's volley and your Android network structure. Goal is to reduce the amount of boilerplate code.

## Setup

**With [gradle](http://tools.android.com/tech-docs/new-build-system):**

* Clone the project under `/libraries` folder of your root project.
* Add these lines to your `settings.gradle`:

```
include 'rally-lib'
project (':rally-lib').projectDir = new File('libraries/rally/rally-lib')
include 'volley'
project (':volley').projectDir = new File('libraries/rally/libraries/volley')
```

* To declare the dependencies, add these lines to your `build.gradle`:

```
dependencies {
...

compile project(':rally-lib')
compile project(':volley')

...
}
```

That's all!

## Usage

Here's how I use rally, but you can easily integrate it in your own way too!

### What do you need?

* Extend your Android Application class to `CoreBaseApplication` for declaring and initialising `volley` instances. (This could be done in another place too, but it's just easier in here)

* Create a `BaseRequest` class that extends `CoreBaseRequest`.
(Don't forget! This whole library is all about more modular and reusable network structure.)

Here is a simple `BaseRequest` class:

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


And here is a simple *POST* request:

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

### How to call?

```
<APPLICATION_CLASS>.getInstance().getRequestQueue().add(new CreateUserRequest(getActivity(), mUser) {
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

`<MODEL_CLASS_OF_YOUR_RESPONSE>` is a response class to retrieve the network response, and `<YourUserClass>` is the request class to send the request to the server. Simple.
Hence you don't have to worry anything about parsing, `gson` handles it in an elegant way.

***

### Other useful methods
* `CoreVolleyUtil.getMethodName`

Returns the `String` value of the HTTP Methods. It might be necessary if you use HTTP verbs as parameters,
(i.e Authorising your calls, since `volley` has `int` implementation of them.)

To-do
---
* tests
* gzip support

License
---

*rally* is available under Apache License Version 2.0. See the [LICENSE.md](LICENSE.md) file for more info.

Get In Touch
---

If you have questions, feel free to post an issue here on GitHub or just email us at [rally@zeplin.io](mailto:rally@zeplin.io).

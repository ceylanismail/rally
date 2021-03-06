# Rally

*rally* is a simple wrapper around Google's *volley* and your Android network structure. The goal is to reduce the amount of boilerplate code.

## Setup

####For gradle users:

Add this line to your dependencies:

```
dependencies {
    ...
    // Rally
    compile 'io.zeplin.rally:rally:1.0.0'
    ...
}
```

## Usage

Here's how we use *rally*, but you can easily integrate it in your own way too!

### What do you need?

* Extend your Android Application class to `CoreBaseApplication` for declaring and initializing `RequestQueue` and `ImageLoader` instances.

* Create a `BaseRequest` class that extends `CoreBaseRequest`.

Here is a simple `BaseRequest` class:

```
public abstract class BaseRequest extends CoreBaseRequest {

    @Override
    protected String baseUrl() {
        return "https://api.example.com";
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

    private final User mUser;

    public CreateUserRequest(Context context, User user) {
        super(context);
        mUser = user;
    }

    @Override
    protected int httpMethod() {
        return Request.Method.POST;
    }

    @Override
    protected String path() {
        return "../user";
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

`UserInfo` is a response class to retrieve the network response, and `User` is the request class to send the request to the server.

Don't worry anything about parsing, `Gson` does it in an elegant way!

***

### Other useful methods
* `CoreVolleyUtil.getMethodName`

Returns the `String` representation of the HTTP Methods defined in *volley* as *integer*. It is handy, when you use HTTP verbs as parameters.

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

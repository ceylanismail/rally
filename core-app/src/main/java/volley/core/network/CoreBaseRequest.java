package volley.core.network;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class CoreBaseRequest<T> {

    /**
     * @return base url of the server
     */
    protected abstract String baseUrl();

    /**
     * GET, POST, PUT, DELETE
     * @return integer value of Http Method
     */
    protected abstract int httpMethod();

    /**
     * @param methodCode integer value of HTTP Methods
     * @return String value of HTTP Methods defined in {@link com.android.volley.Request.Method}
     */
    public static String getMethodName(int methodCode) throws IllegalAccessException {
        Field[] fields = Request.Method.class.getDeclaredFields();

        for (Field field : fields) {
            if (methodCode == field.getInt(null)) {
                return field.getName();
            }
        }

        return null;
    }

    /**
     * @return the url path that will be concatenated
     */
    protected abstract String path();

    /**
     * @return response class of the action
     */
    protected abstract Class<T> responseClass();

    /**
     * @return body of the action
     */
    protected abstract String body();

    /**
     * @return headers, unless any authorization is needed
     */
    protected abstract Map<String, String> getHeaders();

    /**
     * callback method for network responses
     */
    protected void onSuccess(T response) {
        // for rent
    }

    /**
     * callback method for network errors
     */
    protected void onError(VolleyError error) {
        // for rent
    }

    /**
     * @return listener back for network responses
     */
    private Response.Listener<T> successListener() {
        return new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                onSuccess(response);
            }
        };
    }

    /**
     * @return listener back for error
     */
    private Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);
            }
        };
    }

    /**
     * @return whole path of the URL
     */
    private String requestUrl() {
        return baseUrl() + path();
    }

    /**
     * Can be rented for advanced usages
     *
     * @return {@link com.google.gson.Gson object}
     */
    protected Gson gson() {
        return new Gson();
    }

    /**
     * @return new built of Request class
     */
    public Request<T> create() {
        return new CoreGsonRequest<T>(
                gson(),
                httpMethod(),
                requestUrl(),
                responseClass(),
                getHeaders(),
                successListener(),
                errorListener(),
                body()
        );
    }
}

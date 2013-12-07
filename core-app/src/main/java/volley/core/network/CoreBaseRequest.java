package volley.core.network;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

public abstract class CoreBaseRequest<T> {

    /**
     * Essentials to send server the String version of them for authentication
     */
    private static final String[] METHOD_NAMES = {"GET", "POST", "PUT", "DELETE"};

    /**
     * @return base url of the server
     */
    protected abstract String getBaseUrl();
    /**
     * GET, POST, PUT, DELETE
     * @return integer value of Http Method
     */
    protected abstract int httpMethod();

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
     * @param methodCode integer value of Http Methods
     * @return String value of Http Methods
     */
    private String getMethodName(int methodCode) {
        return METHOD_NAMES[methodCode];
    }

    /**
     * @return whole path of the URL
     */
    private String requestUrl() {
        return getBaseUrl() + path();
    }

    /**
     * @return new built of Request class
     */
    public Request<T> create() {
        return new CoreGsonRequest<T>(
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

package io.zeplin.rallysamples.requests;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

import io.zeplin.rally.network.RLYBaseRequest;
import io.zeplin.rally.toolbox.MFieldNamingStrategy;

/**
 * Base Request class which customizes the common params and methods of your application.
 */
public class SampleBaseRequest extends RLYBaseRequest {
    /**
     * Base URL of the network calls.
     * "http://httpbin.org" will be used for the sample calls.
     */
    private static final String BASE_URL = "http://httpbin.org";

    /**
     * Implement this method to return your own BASE_URL.
     * It's recommended that to return a valid BASE_URL in this class.
     * @return BASE_URL of the network calls.
     */
    @Override
    protected String baseUrl() {
        return BASE_URL;
    }

    /**
     * Implement this method to define a default HTTP method of the network calls.
     * It's optional to define a default HTTP method here in Base class, it can be overridden for each calls later on.
     * @return Valid HTTP method. Values must be the ones from {@link com.android.volley.Request.Method} interface.
     */
    @Override
    protected int httpMethod() {
        return Request.Method.GET;
    }

    /**
     * Override this method to add your URL query parameters
     * @return key-value map of the parameters
     */
    @Override
    protected Map<String, String> params() {
        return null;
    }

    /**
     * This method will be used to specify the specific path of the network calls.
     * It's not necessary to implement this here in Base class.
     * Ex: BASE_URL + "/users".
     * @return path of specific call.
     */
    @Override
    protected String path() {
        // ex: return "/users";
        return null;
    }

    /**
     * This method will be used to specify the specific response class for the corresponding calls.
     * It's not necessary to implement this here in Base class, and enough to return the model class for each calls.
     * Gson will be handling all JSON parsing smoothly.
     * @return model class of the response.
     */
    @Override
    protected Class responseClass() {
        // ex: return RLYSampleGetModel.class;
        return null;
    }

    /**
     * Request body for POST, PUT etc..
     * It should be used separately inside the corresponding calls.
     * It is also enough to return the model class for each calls.
     *
     * Note for naming strategy:
     * If you are using Android naming strategy in your model classes (i.e "m" prefix) you can also set it in this method for Gson.
     *
     * P.S: {@link io.zeplin.rally.toolbox.MFieldNamingStrategy} is predefined in Rally, but you can create your own as well.
     * @return body of the request
     */
    @Override
    protected String body() {
        // ex with MFieldNamingStrategy naming strategy: return new GsonBuilder().setFieldNamingStrategy(new MFieldNamingStrategy()).create().toJson(<instance_of_the_model_class>);
        // ex without naming strategy: return <instance_of_the_model_class>;
        return null;
    }

    /**
     * Add your custom headers of your network call.
     * @return key-value map of headers required for the request.
     */
    @Override
    protected Map<String, String> getRequestHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("Accept", "application/json");

        return headers;
    }

    /**
     * Override this method if and only if you are using custom field naming strategy, no need for other cases.
     * @return {@link com.android.volley.Request} data of the network call.
     */
    @Override
    public Request create() {
        return super.create(new MFieldNamingStrategy());
    }
}

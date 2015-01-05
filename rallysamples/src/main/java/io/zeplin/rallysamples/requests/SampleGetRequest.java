package io.zeplin.rallysamples.requests;


import java.util.HashMap;
import java.util.Map;

import io.zeplin.rallysamples.models.RLYSampleGetResponseModel;

public class SampleGetRequest extends BaseRequest {
    /**
     * Override the path to append it to the BASE_URL
     * @return the path of this call
     */
    @Override
    protected String path() {
        // now our full URL will be: "http://httpbin.org/get"
        return "/get";
    }

    /**
     * Override this method to add your URL query parameters
     * @return key-value map of the parameters
     */
    @Override
    protected Map<String, String> params() {
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("param1", "1");
        headers.put("param2", "2");

        // now our full URL will be: "http://httpbin.org/get?param1=1&param2=2"
        return headers;
    }

    /**
     * Override the method to define this response data of this call
     * @return Response class of the request
     */
    @Override
    protected Class responseClass() {
        return RLYSampleGetResponseModel.class;
    }
}

package io.zeplin.rallysamples.requests;

import com.android.volley.Request;
import com.google.gson.GsonBuilder;

import io.zeplin.rally.toolbox.MFieldNamingStrategy;
import io.zeplin.rallysamples.models.RLYSimplePostDataModel;
import io.zeplin.rallysamples.models.RLYSimplePostResponseModel;

public class SamplePostRequest extends BaseRequest {
    /**
     * Request body of the call.
     */
    private final RLYSimplePostDataModel mPostDataModel;

    public SamplePostRequest(RLYSimplePostDataModel postDataModel) {
        mPostDataModel = postDataModel;
    }

    /**
     * We are overriding this method, since we want to make a POST call different than our default (GET)
     * @return POST value of {@link com.android.volley.Request.Method}.
     */
    @Override
    protected int httpMethod() {
        return Request.Method.POST;
    }

    /**
     * Override the path to append it to the BASE_URL.
     * @return the path of this call.
     */
    @Override
    protected String path() {
        // now our full URL will be: "http://httpbin.org/post"
        return "/post";
    }

    /**
     * Override the method to define this response data of this call.
     * @return Response class of the request.
     */
    @Override
    protected Class responseClass() {
        return RLYSimplePostResponseModel.class;
    }

    /**
     * Request body of with {@link io.zeplin.rally.toolbox.MFieldNamingStrategy}
     * You can directly return your data -i.e. mPostDataModel- if you don't have any custom naming strategy.
     * @return body of the request
     */
    @Override
    protected String body() {
        return new GsonBuilder().setFieldNamingStrategy(new MFieldNamingStrategy())
                .create().toJson(mPostDataModel);
    }
}

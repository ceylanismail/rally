package io.zeplin.rallysamples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import io.zeplin.rallysamples.models.RLYSampleGetResponseModel;
import io.zeplin.rallysamples.models.RLYSamplePostDataModel;
import io.zeplin.rallysamples.models.RLYSamplePostResponseModel;
import io.zeplin.rallysamples.requests.SampleGetRequest;
import io.zeplin.rallysamples.requests.SamplePostRequest;


public class MainActivity extends Activity {
    /**
     * {@link com.android.volley.RequestQueue} instance to be used for network requests
     */
    protected RequestQueue mRequestQueue;
    /**
     * {@link com.android.volley.toolbox.ImageLoader} instance to be used for image loading
     */
    protected ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve RequestQueue instance
        mRequestQueue = ((RLYApp) getApplicationContext()).getRequestQueue();
        // Retrieve ImageLoader instance
        mImageLoader = ((RLYApp) getApplicationContext()).getImageLoader();

        sampleGetCall();
        samplePostCall();
    }

    private void sampleGetCall() {
        mRequestQueue.add(new SampleGetRequest() {
            @Override
            protected void onSuccess(Object response) {
                super.onSuccess(response);

                // Get your response data by casting it to your model class
                RLYSampleGetResponseModel myResponse = (RLYSampleGetResponseModel) response;

                Log.i("GETResponse", "My origin data: " + myResponse.getOrigin());
                // OUTPUT: io.zeplin.rallysamples I/GETResponse﹕ My origin data: 192.168.1.0

                Log.i("GETResponse", "My url data: " + myResponse.getUrl());
                // OUTPUT: io.zeplin.rallysamples I/GETResponse﹕ My url data: http://httpbin.org/get

                // Other success cases of your call..
            }

            @Override
            protected void onError(VolleyError error) {
                super.onError(error);
                Log.e("GETResponseError", error.getMessage());

                // Error cases of your call..
            }
        }.create());
    }

    private void samplePostCall() {
        RLYSamplePostDataModel myData =
                new RLYSamplePostDataModel("ExamplePostStringData", 3);

        mRequestQueue.add(new SamplePostRequest(myData) {
            @Override
            protected void onSuccess(Object response) {
                super.onSuccess(response);

                // Get your response data by casting it to your model class
                RLYSamplePostDataModel myResponse = ((RLYSamplePostResponseModel) response).getJson();

                Log.i("POSTResponse", "My string data: " + myResponse.getMyPostStringData());
                // OUTPUT: io.zeplin.rallysamples I/POSTResponse﹕ My string data: ExamplePostStringData

                Log.i("POSTResponse", "My int data: " + myResponse.getMyPostIntData());
                // OUTPUT: io.zeplin.rallysamples I/POSTResponse﹕ My int data: 3

                // Other success cases of your call..
            }

            @Override
            protected void onError(VolleyError error) {
                super.onError(error);
                Log.e("POSTResponseError", error.getMessage());

                // Error cases of your call..
            }
        }.create());
    }
}

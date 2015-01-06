package io.zeplin.rallysamples.models;

import java.io.Serializable;

/**
 * Sample model class will be used to retrieve specific network responses.
 *
 * It's a specific model class for this application, you need to change it with yours.
 */
public class RLYSampleGetResponseModel implements Serializable {
    private String mOrigin;
    private String mUrl;

    public String getOrigin() {
        return mOrigin;
    }

    public String getUrl() {
        return mUrl;
    }
}

package io.zeplin.rallysamples.models;

import java.io.Serializable;

/**
 * Sample model class will be used to retrieve specific network responses.
 *
 * It's a specific model class for this application, you need to change it with yours.
 */
public class RLYSamplePostResponseModel implements Serializable {
    /**
     * This ("json") is the first parameter which will be returning from the post call.
     */
    private RLYSamplePostDataModel mJson;

    public RLYSamplePostDataModel getJson() {
        return mJson;
    }
}

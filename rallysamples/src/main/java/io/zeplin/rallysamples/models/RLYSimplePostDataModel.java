package io.zeplin.rallysamples.models;

/**
 * Sample model class will be used;
 * to retrieve specific network responses, and to send the body for POST request.
 *
 * It's a specific model class for this application, you need to change it with yours.
 */
public class RLYSimplePostDataModel {
    private final String mMyPostStringData;
    private final int mMyPostIntData;

    public RLYSimplePostDataModel(String myPostStringData, int myPostIntData) {
        mMyPostStringData = myPostStringData;
        mMyPostIntData = myPostIntData;
    }

    public String getMyPostStringData() {
        return mMyPostStringData;
    }

    public int getMyPostIntData() {
        return mMyPostIntData;
    }
}

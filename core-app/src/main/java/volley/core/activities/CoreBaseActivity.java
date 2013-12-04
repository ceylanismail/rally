package volley.core.activities;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import volley.core.application.CoreBaseApplication;

public class CoreBaseActivity extends Activity {

    protected RequestQueue mRequestQueue;
    protected ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoreBaseApplication appInstance = CoreBaseApplication.getInstance();

        mRequestQueue = appInstance.getRequestQueue();
        mImageLoader = appInstance.getImageLoader();
    }
}

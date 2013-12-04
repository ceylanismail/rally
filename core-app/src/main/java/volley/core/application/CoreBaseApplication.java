package volley.core.application;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import volley.core.network.CoreVolley;

public class CoreBaseApplication extends Application {

    private static CoreBaseApplication ourInstance;
    public static CoreBaseApplication getInstance() {
        return ourInstance;
    }

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();

        // create network instances
        initNetwork(this);
    }

    /**
     * Initializes network instance
     * @param context application context for Volley instances
     */
    private void initNetwork(Context context) {
        CoreVolley coreVolleyInstance = CoreVolley.getInstance();
        mRequestQueue = coreVolleyInstance.getRequestQueue(context);
        mImageLoader  = coreVolleyInstance.getImageLoader(context);
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        mRequestQueue = requestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }
}

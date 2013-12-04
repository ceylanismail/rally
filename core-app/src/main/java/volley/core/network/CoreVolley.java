package volley.core.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class CoreVolley {

    private static CoreVolley ourInstance = new CoreVolley();
    public static CoreVolley getInstance() {
        return ourInstance;
    }

    private RequestQueue sRequestQueue;
    private ImageLoader sImageLoader;

    public RequestQueue getRequestQueue(Context context) {
        return sRequestQueue == null ? sRequestQueue = Volley.newRequestQueue(context) : sRequestQueue;
    }

    public ImageLoader getImageLoader(Context context) {
        return sImageLoader == null ? sImageLoader = new ImageLoader(getRequestQueue(context), new CoreBitmapLruCache()) : sImageLoader;
    }
}

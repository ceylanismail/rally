package volley.core.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class CoreBitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        return cacheSize;
    }

    public CoreBitmapLruCache() {
        this(getDefaultLruCacheSize());
    }

    public CoreBitmapLruCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

    // TODO: I don't decide to use this now: Ismail 11/28/13
//    @Override
//    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
//        if (!oldValue.isRecycled()) {
//            oldValue.recycle();
//            oldValue = null;
//        }
//    }
}

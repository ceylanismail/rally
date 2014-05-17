/*
* Copyright (C) 2014 Zeplin
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package io.zeplin.rally.application;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import io.zeplin.rally.network.CoreVolley;

public class CoreBaseApplication extends Application {

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

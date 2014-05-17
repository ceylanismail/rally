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
package io.zeplin.rally.network;

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

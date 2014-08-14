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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Volley adapter for JSON requests that will be parsed into Java objects by Gson.
 */
public class CoreGsonRequest<T> extends JsonRequest<T> {

    private final Class<T> mClazz;
    private final Map<String, String> mHeaders;
    private Map<String, String> mResponseHeaders;
    private Gson mGson;

    /**
     * Make a request and return a parsed object from JSON.
     *
     * @param method HTTP method that will be used
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request mHeaders
     * @param listener Success listener
     * @param errorListener Error listener
     * @param body Request body
     */
    public CoreGsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,
                           Response.Listener<T> listener, Response.ErrorListener errorListener,
                           String body, FieldNamingStrategy fieldNamingStrategy) {
        super(method, url, body, listener, errorListener);
        mClazz = clazz;
        mHeaders = headers;

        mGson = new GsonBuilder().setFieldNamingStrategy(fieldNamingStrategy).create();
    }

    public CoreGsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,
                           Response.Listener<T> listener, Response.ErrorListener errorListener,
                           String body) {
        super(method, url, body, listener, errorListener);
        mClazz = clazz;
        mHeaders = headers;

        mGson = new Gson();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (mHeaders == null) {
            return super.getHeaders();
        }

        for (String header : mHeaders.keySet()) {
            if (header.equalsIgnoreCase("content-type")) {
                throw new AuthFailureError("Duplicate Content-Type header definition.");
            }
        }

        return mHeaders;
    }

    public Map<String, String> getResponseHeaders() {
        return mResponseHeaders;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        if (mClazz == null) return Response.success(null, null);
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            mResponseHeaders = response.headers;
            return Response.success(
                    mGson.fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}

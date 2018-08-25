package com.eason.zk.utils.http.impl;

import com.google.gson.Gson;

import com.eason.zk.utils.http.HttpResponseCallbackHandler;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by knightliao on 16/1/7.
 */
public class HttpResponseCallbackHandlerJsonHandler<T> implements HttpResponseCallbackHandler<T> {

    private Class<T> clazz = null;

    public HttpResponseCallbackHandlerJsonHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T handleResponse(String requestBody, HttpEntity entity) throws IOException {

        String json = EntityUtils.toString(entity, "UTF-8");

        Gson gson = new Gson();
        T response = gson.fromJson(json, clazz);

        return response;
    }
}

package com.eason.zk.utils.http;

import org.apache.http.HttpEntity;

import java.io.IOException;

public interface HttpResponseCallbackHandler<T> {

    T handleResponse(String requestBody, HttpEntity entity) throws IOException;
}

package com.eason.zk.restful.type;

import com.eason.zk.restful.core.UnreliableInterface;
import com.eason.zk.utils.http.HttpClientUtil;
import com.eason.zk.utils.http.HttpResponseCallbackHandler;
import com.eason.zk.utils.http.impl.HttpResponseCallbackHandlerJsonHandler;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * RestFul get
 *
 * @author liaoqiqi
 * @version 2014-6-16
 */
public class RestfulGet<T> implements UnreliableInterface {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RestfulGet.class);

    private HttpRequestBase request = null;
    private HttpResponseCallbackHandler<T> httpResponseCallbackHandler = null;

    public RestfulGet(Class<T> clazz, URL url) {

        HttpGet request = new HttpGet(url.toString());
        request.addHeader("content-type", "application/json");
        this.request = request;
        this.httpResponseCallbackHandler = new
                HttpResponseCallbackHandlerJsonHandler<T>(clazz);
    }

    /**
     * Get数据
     */
    @Override
    public T call() throws Exception {

        T value = HttpClientUtil.execute(request, httpResponseCallbackHandler);

        return value;
    }
}

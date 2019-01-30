package com.glinlf.growth.okhttp;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author created by glinlf
 * @data 2019/01/30
 * @remark okhttp kit method
 */
@Component
public class OkHttpKit {

    private static Logger LOG = LoggerFactory.getLogger(OkHttpKit.class);

    /**
     * request config
     */
    private static long HTTP_READ_TIMEOUT = 5000;
    private static long HTTP_WRITE_TIMEOUT = 5000;
    private static long HTTP_CONNECT_TIMEOUT = 10000;
    private static long HTTP_ERROR_TRY_TIME = 3;

    final static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .build();

    /**
     * POST MediaType
     */
    public static final MediaType jsonReq = MediaType.parse("application/json; charset=utf-8");

    /**
     * reqeust headers
     *
     * @param map
     * @return
     */
    public static Headers mapToHeaders(Map<String, String> map) {
        Headers.Builder builder = new Headers.Builder();
        if (Objects.nonNull(map)) {
            for (Map.Entry<String, String> headerEntry : map.entrySet()) {
                builder.add(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        return builder.build();
    }

    /**
     * okhttp get method
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        LOG.info("okhttp get request ：{}", request);
        Response res = client.newCall(request).execute();
        final String result = res.body().string();
        LOG.info("okhttp get result : {}", result);
        if (res.isSuccessful()) {
            return result;
        } else {
            LOG.debug("okhttp get response error, resposse : {}", res);
            throw new IllegalStateException("okhttp get unexpected response code!" + res);
        }

    }

    /**
     * get request with header
     *
     * @param url
     * @param headers
     * @return
     * @throws IOException
     */
    public String get(String url, Map<String, String> headers) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .headers(mapToHeaders(headers))
                .build();
        LOG.info("okhttp get request ：{}", request);
        Response res = client.newCall(request).execute();
        final String result = res.body().string();
        LOG.info("okhttp get result : {}", result);
        if (res.isSuccessful()) {
            return result;
        } else {
            LOG.debug("okhttp get response error, resposse : {}", res);
            throw new IllegalStateException("okhttp get unexpected response code!" + res);
        }
    }

    /**
     * post method Posting a String:
     *
     * @param url
     * @param data
     * @param heads
     * @return
     * @throws IOException
     */
    public String post(String url, String json, Map<String, String> headers) throws IOException {
        json = Objects.isNull(json) ? "" : json;
        FormBody.Builder formBuild = new FormBody.Builder();
        RequestBody body = RequestBody.create(jsonReq, json);
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(mapToHeaders(headers))
                .build();
        LOG.info("okhttp post request ：{}", request);
        final Response res = client.newCall(request).execute();
        final String result = res.body().string();
        LOG.info("okhttp post result : {}", result);
        if (res.isSuccessful()) {
            return result;
        } else {
            LOG.debug("okhttp post response error, resposse : {}", res);
            throw new IllegalStateException("okhttp post unexpected response code!" + res);
        }

    }

    /**
     * post method post form key-value
     *
     * @param url
     * @param data  json body
     * @param heads
     * @return
     * @throws IOException
     */
    public String post(String url, Map<String, Object> data, Map<String, String> headers) throws IOException {
        FormBody.Builder formBuild = new FormBody.Builder();
        if (Objects.isNull(data)) {
            data = new HashMap<>();
        }
        for (Object key : data.keySet()) {
            if (data.get(key) != null) {
                formBuild.addEncoded(String.valueOf(key), String.valueOf(data.get(key)));
            }
        }
        RequestBody body = formBuild.build();
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(mapToHeaders(headers))
                .build();
        LOG.info("okhttp post request ：{}", request);
        final Response res = client.newCall(request).execute();
        final String result = res.body().string();
        LOG.info("okhttp post result : {}", result);
        if (res.isSuccessful()) {
            return result;
        } else {
            LOG.debug("okhttp post response error, resposse : {}", res);
            throw new IllegalStateException("okhttp post unexpected response code!" + res);
        }

    }

    /**
     * async post request
     *
     * @param url
     * @param data  json body
     * @param heads
     * @return
     * @throws IOException
     */
    public void asyncPost(String url, String json, Map<String, String> headers) throws IOException {
        json = Objects.isNull(json) ? "" : json;
        FormBody.Builder formBuild = new FormBody.Builder();
        RequestBody body = RequestBody.create(jsonReq, json);
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(mapToHeaders(headers))
                .build();
        LOG.info("okhttp asyncpost request ：{}", request);
        client.newCall(request).enqueue(new Callback() {//回调方法

            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response res) throws IOException {
                String result = res.body().string();
                LOG.info("okhttp post callback result : {}", result);
                if (res.isSuccessful()) {
                    // 回调操作
                } else {
                    LOG.debug("okhttp post callback response error, resposse : {}", res);
                    throw new IllegalStateException("okhttp post callback unexpected response code!" + res);
                }
            }
        });
    }

    /**
     * http put has body with application/json
     *
     * @param url
     * @param appHeaders
     * @return
     */
    public String put(String url, String json, Map<String, String> headers) throws IOException {
        json = Objects.isNull(json) ? "" : json;
        FormBody.Builder formBuild = new FormBody.Builder();
        RequestBody body = RequestBody.create(jsonReq, json);
        final Request request = new Request.Builder()
                .url(url)
                .put(body)
                .headers(mapToHeaders(headers))
                .build();
        LOG.info("okhttp put request ：{}", request);
        final Response res = client.newCall(request).execute();
        final String result = res.body().string();
        LOG.info("okhttp put result : {}", result);
        if (res.isSuccessful()) {
            return result;
        } else {
            LOG.debug("okhttp put response error, resposse : {}", res);
            throw new IllegalStateException("okhttp put unexpected response code!" + res);
        }
    }

    /**
     * http put no body
     *
     * @param url
     * @param headers
     * @return
     * @throws IOException
     */
    public static String put(String url, Map<String, String> headers) throws IOException {
        RequestBody body = RequestBody.create(jsonReq, "");
        final Request request = new Request.Builder()
                .url(url)
                .put(body)
                .headers(mapToHeaders(headers))
                .build();
        LOG.info("okhttp put request ：{}", request);
        final Response res = client.newCall(request).execute();
        final String result = res.body().string();
        LOG.info("okhttp put result : {}", result);
        if (res.isSuccessful()) {
            return result;
        } else
            LOG.debug("okhttp put response error, resposse : {}", res);
        throw new IllegalStateException("okhttp put unexpected response code!" + res);
    }

}

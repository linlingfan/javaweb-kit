package com.glinlf.growth.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author created by glinlf
 * @data 2018/6/27
 * @remark 小程序生成跳转指定页面的二维码
 */
public class MiniAppQrCode {

    static final Logger LOG = LoggerFactory.getLogger(MiniAppQrCode.class);

    private static OkHttpClient client = new OkHttpClient.Builder().build();
    public static final MediaType jsonReq = MediaType.parse("application/json; charset=utf-8");

    /**
     * 获取 生成小程序二维码
     *
     * @throws IOException
     */
    public void createQrCode() throws IOException {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        // todo get access_token
        String accessToken = "access_token";
        String appPath = "pages/look/lookStepOne";
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacode?access_token=" + accessToken;
            Map<String, Object> param = new HashMap<>();
            param.put("path", appPath);
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            param.put("is_hyaline", false);
            String json = JsonHelper.toJson(param);
            RequestBody body = RequestBody.create(jsonReq, json);
            final Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .header("Content-Type", "application/json")
                    .build();
            String content = "";
            final Response res = client.newCall(request).execute();

            if (res.isSuccessful()) {
                ResponseBody responseBody = res.body();
                LOG.info("调用小程序生成微信永久小程序码URL接口返回结果:" + responseBody);
                inputStream = responseBody.byteStream();

                File file = new File("/Users/linlingfan/Pictures/qrcode.jpg");
                if (!file.exists()) {
                    file.createNewFile();
                }
                outputStream = new FileOutputStream(file);
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                outputStream.flush();
            } else {
                LOG.error("post response error ,content : " + res);
                throw new IOException("http post Unexpected code " + res);
            }

        } catch (Exception e) {
            LOG.error("调用小程序生成微信永久小程序码URL接口异常", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}


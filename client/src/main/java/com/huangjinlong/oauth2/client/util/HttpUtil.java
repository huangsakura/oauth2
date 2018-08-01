package com.huangjinlong.oauth2.client.util;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class HttpUtil {

    private HttpUtil(){}

    public static String postMap(String url, Map<String,String> params) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()){
            builder = builder.add(key,params.get(key));
        }

        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = new OkHttpClient().newCall(request).execute();
        return response.body().string();
    }
}

package com.tinno.aimap.service;

import android.annotation.SuppressLint;

import com.tinno.aimap.model.ResultBean;
import com.tinno.aimap.model.TokenBean;

import java.io.IOException;
import java.util.Base64;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.tinno.aimap.utils.Util.getGson;

public class AIService {
    String BASE_URL = "https://openapi.baidu.com/";

//    @FormUrlEncoded
//    @POST("rest/2.0/mms/v1/general/generalInfo")
//    Call<JsonResultBean> recognize(@FieldMap Map<String,String> fields);

    public static final String TOKEN_URL = "https://aip.baidubce.com/oauth/2.0/token";
    public static final String AI_URL = "https://openapi.baidu.com/rest/2.0/mms/v1/general/generalInfo";

    private static class HttpClientHolder {
        private static final OkHttpClient INSTANCE = new OkHttpClient();
    }




    public static final OkHttpClient getHttpClient() {
        return HttpClientHolder.INSTANCE;
    }


    public static String getTokenJson() throws IOException {
        Request request = buildTokenRequest();
        Response response = getHttpClient().newCall(request).execute();
        ResponseBody body = response.body();
        if (body != null) {
            return body.string();
        }
        return null;
    }

    public static String getToken() throws IOException {
        String tokenResult = getTokenJson();
        return parseToken(tokenResult);
    }

    public static String parseToken(String json) throws IOException {
        TokenBean tokenBean = getGson().fromJson(json, TokenBean.class);
        return tokenBean.getAccessToken();
    }

    public static ResultBean parseResult(String jsonResult) {
        return getGson().fromJson(jsonResult, ResultBean.class);
    }

    @SuppressLint("NewApi")
    public static String recognize(String token, String base64String) throws IOException {
        Request request = buildImgRecgRequest(token, base64String);
        Response response = getHttpClient().newCall(request).execute();
        ResponseBody body = response.body();
        if (body != null) {
            return body.string();
        }
        return null;
    }


    @SuppressLint("NewApi")
    public static String recognize(String token, byte[] jpgByte) throws IOException {
        String img64 = Base64.getEncoder().encodeToString(jpgByte);
        Request request = buildImgRecgRequest(token, img64);
        Response response = getHttpClient().newCall(request).execute();
        ResponseBody body = response.body();
        if (body != null) {
            return body.string();
        }
        return null;
    }

    private static Request buildTokenRequest() {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", "zP0b7bjP1oXxwQGeZRrYtkxPRq11T3d0")
                .add("client_secret", "3BGv0l41KP5og0BW4Sj6GjFZGac90kmv")
                .add("grant_type", "client_credentials")
                .build();
        return new Request.Builder().url(AIService.TOKEN_URL)
                .post(formBody)
                .build();
    }

    //"Content-Type","application/x-www-form-urlencoded"
    private static Request buildImgRecgRequest(String token, String image64) {
        RequestBody requestBody = new FormBody.Builder()
                .add("access_token", token)
                .add("image", image64)
                .add("ecology_uid", "1234567890")
                .add("eco", "general")
                .build();
        return new Request.Builder().post(requestBody).url(AIService.AI_URL).build();
    }

}

package com.tinno.aimap.service;

import android.annotation.SuppressLint;

import com.tinno.aimap.model.RecgResultBean;
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

/**
 * request token:
 * url:https://aip.baidubce.com/oauth/2.0/token
 *
 * Content-Type:application/x-www-form-urlencoded
 * body:
 * 15899877054
 * 3820kang.
 * ClientId    fw0EqE0I20QNV9uSbbanlOx0s5riAIOl
 * ClientSecret    uRocuYzwa7XgUVut3rxPpoDEH35sSpLw
 *
 * ------------------------------------------------------
 * grant_type:client_credentials
 * client_id:zP0b7bjP1oXxwQGeZRrYtkxPRq11T3d0
 * client_secret:3BGv0l41KP5og0BW4Sj6GjFZGac90kmv
 * client_app_id:1585928312337326
 *
*/
    public static final String TOKEN_URL = "https://aip.baidubce.com/oauth/2.0/token";

    /**
     * Object Recognition API
     * */
    public static final String RECG_URL = "https://openapi.baidu.com/rest/2.0/mms/v1/general/generalInfo";
    /**
     * Optical Character Recognition API
    * */
    public static final String OCR_URL = "https://openapi.baidu.com/rest/2.0/mms/v1/ocr/ocrWords";

    private static final String CLIENT_ID = "zP0b7bjP1oXxwQGeZRrYtkxPRq11T3d0";
    private static final String CLIENT_SECRET = "3BGv0l41KP5og0BW4Sj6GjFZGac90kmv";
    private static final String GRANT_TYPE = "client_credentials";

    private static final String ECO = "tangguo";

    private static String sAccessToken;

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

    public static RecgResultBean parseRecgResult(String jsonResult) {
        return getGson().fromJson(jsonResult, RecgResultBean.class);
    }

    @SuppressLint("NewApi")
    public static String objectRecognize(String token, String base64String) throws IOException {
        Request request = buildRecgRequest(token, base64String);
        Response response = getHttpClient().newCall(request).execute();
        ResponseBody body = response.body();
        if (body != null) {
            return body.string();
        }
        return null;
    }


    @SuppressLint("NewApi")
    public static String objectRecognize(String token, byte[] jpgByte) throws IOException {
        String img64 = Base64.getEncoder().encodeToString(jpgByte);
        return objectRecognize(token, img64);
    }

    @SuppressLint("NewApi")
    public static String characterRcognize(String token, String base64String) throws IOException{
        Request request = buildOcrRequest(token, base64String);
        Response response = getHttpClient().newCall(request).execute();
        ResponseBody body = response.body();
        if (body != null) {
            return body.string();
        }
        return null;
    }

    @SuppressLint("NewApi")
    public static String characterRcognize(String token, byte[] jpgByte) throws IOException{
        String img64 = Base64.getEncoder().encodeToString(jpgByte);
        return characterRcognize(token, img64);
    }

    private static Request buildTokenRequest() {
        RequestBody formBody = new FormBody.Builder()
                .add(RequestKey.CLIENT_ID, CLIENT_ID)
                .add(RequestKey.CLIENT_SECRET, CLIENT_SECRET)
                .add(RequestKey.GRANT_TYPE, GRANT_TYPE)
                .build();
        return new Request.Builder().url(AIService.TOKEN_URL)
                .post(formBody)
                .build();
    }

    private static Request buildRecgRequest(String token, String image64) {
        RequestBody requestBody = new FormBody.Builder()
                .add(RequestKey.ACCESS_TOKEN, token)
                .add(RequestKey.IMAGE, image64)
                .add(RequestKey.ECOLOGY_UID, "1234567890")
                .add(RequestKey.ECO, ECO)
                .build();
        return new Request.Builder().post(requestBody).url(AIService.RECG_URL).build();
    }

    private static Request buildOcrRequest(String token, String image64) {
        RequestBody requestBody = new FormBody.Builder()
                .add(RequestKey.ACCESS_TOKEN, token)
                .add(RequestKey.IMAGE, image64)
                .build();
        return new Request.Builder().post(requestBody).url(AIService.OCR_URL).build();
    }

}

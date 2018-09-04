package com.tinno.aimap.service;

import android.os.Handler;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.util.Log;

import com.tinno.aimap.model.OcrResponseBean;
import com.tinno.aimap.model.RecgResponseBean;
import com.tinno.aimap.model.ResponseBean;
import com.tinno.aimap.model.TokenBean;
import com.tinno.aimap.utils.GsonHelper;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Base64;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BaiduService {
    private static final String TAG = "BaiduService";

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

    //TODO auth
    //formal id
//    private static final String CLIENT_ID = "fw0EqE0I20QNV9uSbbanlOx0s5riAIOl";
//    private static final String CLIENT_SECRET = "uRocuYzwa7XgUVut3rxPpoDEH35sSpLw";
    //demo id
    private static final String CLIENT_ID = "zP0b7bjP1oXxwQGeZRrYtkxPRq11T3d0";
    private static final String CLIENT_SECRET = "3BGv0l41KP5og0BW4Sj6GjFZGac90kmv";
    private static final String GRANT_TYPE = "client_credentials";

    private static final String ECO = "tangguo";

    private String mAccessToken;
    private WeakReference<ResponseListener> mListenerRef;

    interface ResponseListener{
        void onCancelled();
        void onConnectivityError(IOException e);
        void onHttpError(int code, String message);
        void onResponseError();
        void onRecgResult(RecgResponseBean bean);
        void onOcrResult(OcrResponseBean bean);
    }

    private android.os.Handler mHandler;

    private Callback mResponseCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            if (mListenerRef != null) {
                return;
            }
            if (call.isCanceled()) {
            } else {
                if (mHandler != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            handleConnectivityError(e);
                        }
                    });
                } else {
                    handleConnectivityError(e);
                }
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (mListenerRef == null) {
                return;
            }
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    String string = body.string();
                    ResponseBean responseBean = GsonHelper.getResponseBean(string);
                    Log.i(TAG, "onResponse: "+responseBean.getErrorCode());
                    switch (responseBean.getErrorCode()) {
                        case ErrorCode.SUCCESS:
                            String url = call.request().url().toString();
                            Log.i(TAG, "onResponse: SUCCESS "+url);
                            if (RECG_URL.equals(url)) {
                                RecgResponseBean bean = GsonHelper.getRecgResponseBean(string);
                                if (mHandler != null) {
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            handleRecgReponse(bean);
                                        }
                                    });
                                } else {
                                    handleRecgReponse(bean);
                                }
                            } else if (OCR_URL.equals(url)) {
                                OcrResponseBean bean = GsonHelper.getOcrResponseBean(string);
                                if (mHandler != null) {
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            handleOcrReponse(bean);
                                        }
                                    });
                                } else {
                                    handleOcrReponse(bean);
                                }
                            }
                            break;
                        case ErrorCode.ACCESS_TOKEN_EXPIRED:
                            Log.i(TAG, "onResponse: ACCESS_TOKEN_EXPIRED");
                            //TODO handle this
                            break;
                        case ErrorCode.NO_PERMISSION:
                        case ErrorCode.NO_RESPONSE_RETURN:
                        default:
                            if (mHandler != null) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        handleHttpError(responseBean.getErrorCode(), responseBean.getErrorMsg());
                                    }
                                });
                            } else {
                                handleHttpError(responseBean.getErrorCode(),responseBean.getErrorMsg());
                            }
                    }
                }
            } else {
                int code = response.code();
                String message = response.message();
                if (mHandler != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            handleHttpError(code, message);
                        }
                    });
                } else {
                    handleHttpError(code, message);
                }
            }
        }
    };

    private void handleHttpError(int code, String message) {
        ResponseListener listener = mListenerRef.get();
        if (listener != null) {
            listener.onHttpError(code, message);
        }
    }

    private void handleResponseError() {
        ResponseListener listener = mListenerRef.get();
        if (listener != null) {
            listener.onResponseError();
        }
    }

    private void handleRecgReponse(RecgResponseBean bean) {
        ResponseListener listener = mListenerRef.get();
        if (listener != null) {
            listener.onRecgResult(bean);
        }
    }

    private void handleOcrReponse(OcrResponseBean bean) {
        ResponseListener listener = mListenerRef.get();
        if (listener != null) {
            listener.onOcrResult(bean);
        }
    }

    private void handleConnectivityError(IOException e) {
        ResponseListener listener = mListenerRef.get();
        if (listener != null) {
            listener.onConnectivityError(e);
        }
    }

    private OkHttpClient mClient = new OkHttpClient();

    public String getTokenJson() throws IOException {
        Request request = buildTokenRequest();
        Response response = mClient.newCall(request).execute();
        ResponseBody body = response.body();
        if (body != null) {
            return body.string();
        }
        return null;
    }

    public String getToken() throws IOException {
        String tokenResult = getTokenJson();
        TokenBean tokenBean = GsonHelper.getTokenBean(tokenResult);
        return tokenBean.getAccessToken();
    }

    public String objectRecognize(String base64String) throws IOException {
        Request request = buildRecgRequest(mAccessToken, base64String);
        return getResponseBody(request);
    }

    public String objectRecognize(byte[] jpgByte) throws IOException {
        String img64 = Base64.getEncoder().encodeToString(jpgByte);
        return objectRecognize(img64);
    }

    public String characterRecognize(String base64String) throws IOException{
        Request request = buildOcrRequest(mAccessToken, base64String);
        return getResponseBody(request);
    }

    @Nullable
    private String getResponseBody(Request request) throws IOException {
        try (Response response = mClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                return body.string();
            }
        } catch (IOException ioe) {
            //TODO handle error code
        }
        return null;
    }

    public void objectRecognizeAsync(String base64String) throws IOException {
        Request request = buildRecgRequest(mAccessToken, base64String);
        mClient.newCall(request).enqueue(mResponseCallback);
    }

    public void characterRecognizeAsync(String base64String) throws IOException{
        Request request = buildOcrRequest(mAccessToken, base64String);
        mClient.newCall(request).enqueue(mResponseCallback);
    }

    public String characterRecognize(byte[] jpgByte) throws IOException{
        String img64 = Base64.getEncoder().encodeToString(jpgByte);
        return characterRecognize(img64);
    }

    private static Request buildTokenRequest() {
        RequestBody formBody = new FormBody.Builder()
                .add(RequestKey.CLIENT_ID, CLIENT_ID)
                .add(RequestKey.CLIENT_SECRET, CLIENT_SECRET)
                .add(RequestKey.GRANT_TYPE, GRANT_TYPE)
                .build();
        return new Request.Builder().url(BaiduService.TOKEN_URL)
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
        return new Request.Builder().post(requestBody).url(BaiduService.RECG_URL).build();
    }

    private static Request buildOcrRequest(String token, String image64) {
        RequestBody requestBody = new FormBody.Builder()
                .add(RequestKey.ACCESS_TOKEN, token)
                .add(RequestKey.IMAGE, image64)
//                .add(RequestKey.POS, "1")
                .build();
        return new Request.Builder().post(requestBody).url(BaiduService.OCR_URL).build();
    }

    public void cancel() {
        mClient.dispatcher().cancelAll();
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public void setResponseListener(ResponseListener listener) {
        mListenerRef = new WeakReference<ResponseListener>(listener);
    }

    public void setResponseListener(ResponseListener listener, Handler handler) {
        mListenerRef = new WeakReference<ResponseListener>(listener);
        mHandler = handler;
    }

    @StringDef({RequestKey.ACCESS_TOKEN, RequestKey.CLIENT_ID,
            RequestKey.CLIENT_SECRET, RequestKey.GRANT_TYPE,
            RequestKey.IMAGE, RequestKey.ECOLOGY_UID, RequestKey.ECO,RequestKey.POS})
    public @interface RequestKey {
        public static final String CLIENT_ID = "client_id";
        public static final String CLIENT_SECRET = "client_secret";
        public static final String GRANT_TYPE = "grant_type";
        public static final String ACCESS_TOKEN = "access_token";
        public static final String IMAGE = "image";
        public static final String ECOLOGY_UID = "ecology_uid";
        public static final String ECO = "eco";
        public static final String POS = "pos";
    }

    @IntDef({ErrorCode.ACCESS_TOKEN_EXPIRED, ErrorCode.SUCCESS,
            ErrorCode.NO_RESPONSE_RETURN, ErrorCode.NO_PERMISSION})
    public @interface ErrorCode {
        int ACCESS_TOKEN_EXPIRED = 111;
        int SUCCESS = 0;
        int NO_RESPONSE_RETURN = 328008;
        int NO_PERMISSION = 6;
    }

}

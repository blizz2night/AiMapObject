package com.tinno.aimap;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

    public static TokenJson parseTokenJson(String string) {
        Gson gson = new Gson();
        TokenJson tokenJson = gson.fromJson(string, TokenJson.class);
        return tokenJson;
    }

    public static Json parseJSONWithJSONObject(String string) {
        Log.d("aaa", "parseJSONWithJSONObject 0 string = " + string);
        Json json = new Json();

        try {
            JSONObject jsonObject = new JSONObject(string);
            Log.d("aaa", "parseJSONWithJSONObject 1 jsonObject = " + jsonObject);
            getJsonResult(jsonObject, json);
            getJsonSupport(jsonObject, json);
            getJsonLogId(jsonObject, json);
            getJsonErrorMsg(jsonObject, json);
            getJsonErrorCode(jsonObject, json);
        } catch (JSONException e) {
            Log.d("aaa", "parseJSONWithJSONObject e = " + e);
            e.printStackTrace();
        }

        return json;
    }

    private static void getJsonResult(JSONObject jsonObject, Json json) {
        try {
            Object result = jsonObject.get("result");
            if (result instanceof JSONObject) {
                JSONObject resultObject = (JSONObject) result;
                String url = (String) resultObject.get("url");
                json.setResult(true);
                json.setUrl(url);
            } else {
                json.setResult((Boolean) result);
                json.setUrl("");
            }
        } catch (JSONException e) {
            json.setResult(false);
            json.setUrl("");
            e.printStackTrace();
        }
    }

    private static void getJsonSupport(JSONObject jsonObject, Json json) {
        try {
            String support = (String) jsonObject.get("support");
            json.setSupport(support);
        } catch (JSONException e) {
            json.setSupport("");
            e.printStackTrace();
        }
    }

    private static void getJsonLogId(JSONObject jsonObject, Json json) {
        try {
            String logId = (String) jsonObject.get("logid");
            json.setLogId(logId);
        } catch (JSONException e) {
            json.setLogId("");
            e.printStackTrace();
        }
    }

    private static void getJsonErrorMsg(JSONObject jsonObject, Json json) {
        try {
            String errorMsg = (String) jsonObject.get("error_msg");
            json.setErrorMsg(errorMsg);
        } catch (JSONException e) {
            json.setErrorMsg("-");
            e.printStackTrace();
        }
    }

    private static void getJsonErrorCode(JSONObject jsonObject, Json json) {
        try {
            int errorCode = (int) jsonObject.get("error_code");
            json.setErrorCode(errorCode);
        } catch (JSONException e) {
            json.setErrorCode(0);
            e.printStackTrace();
        }
    }

    public static class TokenJson {
        String scope;
        String access_token;
        String expires_in;

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }
    }

    public static class Json {
        boolean result;
        String url;
        String support;
        String logId;
        String errorMsg;
        int errorCode;

        public boolean getResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSupport() {
            return support;
        }

        public void setSupport(String support) {
            this.support = support;
        }

        public String getLogId() {
            return logId;
        }

        public void setLogId(String logId) {
            this.logId = logId;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }
    }

}

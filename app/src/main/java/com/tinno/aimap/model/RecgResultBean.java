package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class RecgResultBean {
    private Map<String, Object> result;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("error_msg")
    private String errorMsg;
    private String support;
    private String logid;

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    @Override
    public String toString() {
        return "JsonResultBean{" +
                "result=" + result +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", support='" + support + '\'' +
                ", logid='" + logid + '\'' +
                '}';
    }
}

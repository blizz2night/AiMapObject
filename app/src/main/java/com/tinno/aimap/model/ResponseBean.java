package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

public class ResponseBean {
    @SerializedName("error_code")
    protected int errorCode;
    @SerializedName("error_msg")
    protected String errorMsg;
    protected String support;
    protected String logid;

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
}

package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

public class TokenBean {
    private String scope;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private int maxAge;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public String toString() {
        return "TokenBean{" +
                "scope='" + scope + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", maxAge=" + maxAge +
                '}';
    }
}

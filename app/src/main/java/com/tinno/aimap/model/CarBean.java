package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarBean extends ObjectBean {
    @SerializedName(value = "abstract")
    protected List<BaikeBean> baike;

    @Override
    public List<BaikeBean> getBaike() {
        return baike;
    }

    @Override
    public void setBaike(List<BaikeBean> baike) {
        this.baike = baike;
    }

    public String toString() {
        return "CarBean{" +
                "words='" + words + '\'' +
                ", baike=" + baike +
                '}';
    }
}

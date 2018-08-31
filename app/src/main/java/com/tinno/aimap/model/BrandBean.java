package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

public class BrandBean {

    /**
     * code : moto22
     * name_cn : 雷克萨斯
     * name_en : LEXUS
     * snippet : 雷克萨斯是日本丰田集团旗下全球著名豪华汽车品牌。创立于1983年，1999年起至今，其连续位居北美豪华汽车销量第一的宝座。
     * location : {"left":24,"top":22,"width":88,"height":64}
     * type : 0
     * name : 雷克萨斯
     * thumburl : https://graph.baidu.com/thumb/moto22.jpg
     * probability : 0.99998807907104
     * baikeurl : http://baike.baidu.com/item/%E9%9B%B7%E5%85%8B%E8%90%A8%E6%96%AF/915889
     */

    private String name;
    @SerializedName(value = "snippet")
    private String description;
    @SerializedName(value = "baikeurl")
    private String linkUrl;
    @SerializedName(value = "thumburl")
    private String thumbUrl;
    private LocationBean location;
    @SerializedName(value = "probability")
    private double score;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public String toString() {
        return "BrandBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", location=" + location +
                ", score=" + score +
                '}';
    }
}

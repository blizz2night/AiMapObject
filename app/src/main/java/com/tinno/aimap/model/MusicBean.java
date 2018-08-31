package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

public class MusicBean {


    /**
     * name : 范特西
     * publishCompany : BMG
     * publishTime : &nbsp;2001-09-14
     * genre : &nbsp;流行
     * linkurl : http://music.douban.com/subject/1403307/
     * asn : 周杰伦
     * thumburl : http://t11.baidu.com/it/u=4256600410,2545529356&fm=87
     * objurl : http://6be1a40dd4845cecf2793aaddcc7a682.jpg
     * score : 155.70213317871
     */

    private String name;
    @SerializedName(value = "linkurl")
    private String linkUrl;
    @SerializedName(value = "thumburl")
    private String thumbUrl;
    private String description;
    private double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "name='" + name + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", description='" + description + '\'' +
                ", score=" + score +
                '}';
    }
}

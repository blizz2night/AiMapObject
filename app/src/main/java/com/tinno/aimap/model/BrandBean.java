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
    private String name_cn;
    private String name_en;
    @SerializedName(value = "snippet")
    private String description;
    @SerializedName(value = "baikeurl")
    private String linkurl;
    private String thumburl;
    private LocationBean location;
    private double probability;


    public String getName_cn() {
        return name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

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

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public static class LocationBean {
        /**
         * left : 24
         * top : 22
         * width : 88
         * height : 64
         */

        private int left;
        private int top;
        private int width;
        private int height;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return "LocationBean{" +
                    "left=" + left +
                    ", top=" + top +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BrandBean{" +
                "name='" + name + '\'' +
                ", name_cn='" + name_cn + '\'' +
                ", name_en='" + name_en + '\'' +
                ", description='" + description + '\'' +
                ", linkurl='" + linkurl + '\'' +
                ", thumburl='" + thumburl + '\'' +
                ", location=" + location +
                ", probability=" + probability +
                '}';
    }
}

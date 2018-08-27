package com.tinno.aimap.model;

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

    private String code;
    private String name_cn;
    private String name_en;
    private String snippet;
    private LocationBean location;
    private int type;
    private String name;
    private String thumburl;
    private double probability;
    private String baikeurl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getBaikeurl() {
        return baikeurl;
    }

    public void setBaikeurl(String baikeurl) {
        this.baikeurl = baikeurl;
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
    }
}

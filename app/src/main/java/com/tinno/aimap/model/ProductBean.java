package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

public class ProductBean {

    /**
     * title : Nestle/雀巢咖啡1+2三合一咖啡速溶咖啡原味盒装20条300g
     * brand : 雀巢
     * price : 2590
     * thumburl : https://graph.baidu.com/thumb/3283672383,2286365564.jpg
     * url : http://item.jd.com/13113867293.html
     * category : 咖啡/奶茶
     * place : jd
     */

    @SerializedName(value = "title")
    private String name;
    private String thumbUrl;
    @SerializedName(value = "url")
    private String linkUrl;

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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String url) {
        this.linkUrl = url;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "name='" + name + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                '}';
    }
}

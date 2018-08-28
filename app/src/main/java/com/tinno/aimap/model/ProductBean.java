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
    private String thumburl;
    @SerializedName(value = "url")
    private String linkurl;

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

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String url) {
        this.linkurl = url;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "name='" + name + '\'' +
                ", thumburl='" + thumburl + '\'' +
                ", linkurl='" + linkurl + '\'' +
                '}';
    }
}

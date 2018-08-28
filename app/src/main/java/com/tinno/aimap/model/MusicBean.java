package com.tinno.aimap.model;

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
    private String linkurl;
    private String thumburl;
    private String objurl;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getObjurl() {
        return objurl;
    }

    public void setObjurl(String objurl) {
        this.objurl = objurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "name='" + name + '\'' +
                ", linkurl='" + linkurl + '\'' +
                ", thumburl='" + thumburl + '\'' +
                ", objurl='" + objurl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

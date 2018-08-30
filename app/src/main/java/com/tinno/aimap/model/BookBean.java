package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

public class BookBean {
    /**
     * title : C Primer Plus
     * pages : 626
     * author : ["Stephen Prata","云巅工作室"]
     * binding : 平装(无盘)
     * price : ￥60.00
     * intro : 《C Primer Plus（第5版）（中文版）》共17章。第1、2章学习C语言编程所需的预备知识。第3到15章介绍了C语言的相关知识，包括数据类型、格式化输入输出、运算符、表达式、流程控制语句、函数、数组和指针、字符串操作、内存管理、位操作等等，知识内容都针对C99标准；另外，第10章强化了对指针的讨论，第12章引入了动态内存分配的概念，这些内容更加适合读者的需求。第16章和第17章讨论了C预处理器和C库函数、高级数据表示（数据结构）方面的内容。附录给出了各章后面复习题、编程练习的答案和丰富的C编程参考资料。
     * publisher : 人民邮电出版社
     * linkUrl : http://book.douban.com/subject/1240002/
     * isbn : 9787115130228
     * publish_year : 2005
     * title_url : http://book.douban.com/subject/1240002/
     * thumbUrl : http://t11.baidu.com/it/u=1244168767,2608534125&fm=87
     * objurl : http://img3.doubanio.com/lpic/s1308874.jpg
     * score : 29.760648727417
     */

    @SerializedName(value = "title")
    private String name;
    @SerializedName(value = "intro")
    private String description;
    @SerializedName(value = "linkUrl")
    private String linkUrl;
    @SerializedName(value = "thumbUrl")
    private String thumbUrl;

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

    @Override
    public String toString() {
        return "BookBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                '}';
    }
}

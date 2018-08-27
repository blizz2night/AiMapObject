package com.tinno.aimap.model;

import java.util.List;

public class BookBean {
    /**
     * title : C Primer Plus
     * pages : 626
     * author : ["Stephen Prata","云巅工作室"]
     * binding : 平装(无盘)
     * price : ￥60.00
     * intro : 《C Primer Plus（第5版）（中文版）》共17章。第1、2章学习C语言编程所需的预备知识。第3到15章介绍了C语言的相关知识，包括数据类型、格式化输入输出、运算符、表达式、流程控制语句、函数、数组和指针、字符串操作、内存管理、位操作等等，知识内容都针对C99标准；另外，第10章强化了对指针的讨论，第12章引入了动态内存分配的概念，这些内容更加适合读者的需求。第16章和第17章讨论了C预处理器和C库函数、高级数据表示（数据结构）方面的内容。附录给出了各章后面复习题、编程练习的答案和丰富的C编程参考资料。
     * publisher : 人民邮电出版社
     * linkurl : http://book.douban.com/subject/1240002/
     * isbn : 9787115130228
     * publish_year : 2005
     * title_url : http://book.douban.com/subject/1240002/
     * thumburl : http://t11.baidu.com/it/u=1244168767,2608534125&fm=87
     * objurl : http://img3.doubanio.com/lpic/s1308874.jpg
     * score : 29.760648727417
     */

    private String title;
    private String pages;
    private String binding;
    private String price;
    private String intro;
    private String publisher;
    private String linkurl;
    private String isbn;
    private String publish_year;
    private String title_url;
    private String thumburl;
    private String objurl;
    private double score;
    private List<String> author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(String publish_year) {
        this.publish_year = publish_year;
    }

    public String getTitle_url() {
        return title_url;
    }

    public void setTitle_url(String title_url) {
        this.title_url = title_url;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }
}

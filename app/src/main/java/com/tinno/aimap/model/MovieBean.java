package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

public class MovieBean {

    /**
     * id : http://movie.douban.com/subject/6860160/
     * name : 悲惨世界 Les Misérables
     * aggregateRating :
     * release_data :
     * area : 美国/英国
     * starring : 休·杰克曼/罗素·克劳/安妮·海瑟薇/阿曼达·塞弗里德/埃迪·雷德梅恩/海伦娜·伯翰·卡特/萨莎·拜伦·科恩/萨曼莎·巴克斯/艾伦·特维特/康姆·威尔金森/伊莎贝尔·艾伦/丹尼尔·赫特斯通
     * duration : 157分钟
     * genre : 剧情/爱情/音乐
     * description : 贫苦的冉·阿让（休·杰克曼 Hugh Jackman 饰）为了挨饿的孩子去偷面包，结果被判处19年的苦役。出狱后，走投无路的他偷走了收留他过夜的主教的银器潜逃，被警察捉回。主教声称银器是送给他的，使他免于被捕。主教的言行感化了他，他化名马德兰，从此洗心革面奋发向上开始新生活。但缉拿过他的警长沙威（罗素·克劳 Russell Crowe 饰）却一心要找他麻烦。在得知了芳汀（安妮·海瑟薇 Anne Hathaway 饰）的悲惨遭遇后，他承诺照顾她的私生女柯赛特（阿曼达·塞弗里德 Amanda Seyfried 饰）。八年后，柯赛特爱上了共和派青年马利尤斯（埃迪·雷德梅恩 Eddie Redmayne 饰），轰轰烈烈的巴黎人民起义爆发了，无赖德纳迪埃（萨莎·拜伦·科恩 Sacha Baron Cohen 饰）和他又狭路相逢，而多年来从未放弃追捕他的沙威又出现在他的面前…… &copy;豆瓣
     * cast :
     * linkurl : http://movie.douban.com/subject/6860160/
     * director : 汤姆·霍珀
     * douban_score : 8.5
     * thumburl : http://t10.baidu.com/it/u=1613751570,2588254988&fm=87
     * objurl : http://img3.doubanio.com/view/photo/m/public/p1855112543.jpg
     * score : 148.87255859375
     */

    private String name;
    private String description;
    @SerializedName(value = "linkurl")
    private String linkUrl;
    @SerializedName(value = "thumburl")
    private String thumbUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "MovieBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                '}';
    }
}

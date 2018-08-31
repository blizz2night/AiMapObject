package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

public class FaceBean {
    private String name;
    private String description;
    private String linkUrl;
    private String thumbUrl;
    private LocationBean location;
    private double score;

    public FaceBean(String name) {
        this.name = name;
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

    public static class Outter{
        private String words;
        private String baike;

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        public String getBaike() {
            return baike;
        }

        public void setBaike(String baike) {
            this.baike = baike;
        }
    }

    @Override
    public String toString() {
        return "FaceBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", location=" + location +
                ", score=" + score +
                '}';
    }

    public static class Inner{
        /**
         * name : 赵丽颖
         * tnurl : http://t10.baidu.com/it/u=661626891,1438464912&fm=87
         * baike : see in FurInner
         * face_y : 273
         * face_num : 1
         * face_x : 520
         * ratio : 0.9251674413681
         * face_w : 142
         * face_h : 133
         */
        private String name;
        private String tnurl;
        private String baike;
        private int face_y;
        private int face_num;
        private int face_x;
        @SerializedName(value = "ratio")
        private double score;
        private int face_w;
        private int face_h;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTnurl() {
            return tnurl;
        }

        public void setTnurl(String tnurl) {
            this.tnurl = tnurl;
        }

        public String getBaike() {
            return baike;
        }

        public void setBaike(String baike) {
            this.baike = baike;
        }

        public int getFace_y() {
            return face_y;
        }

        public void setFace_y(int face_y) {
            this.face_y = face_y;
        }

        public int getFace_num() {
            return face_num;
        }

        public void setFace_num(int face_num) {
            this.face_num = face_num;
        }

        public int getFace_x() {
            return face_x;
        }

        public void setFace_x(int face_x) {
            this.face_x = face_x;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getFace_w() {
            return face_w;
        }

        public void setFace_w(int face_w) {
            this.face_w = face_w;
        }

        public int getFace_h() {
            return face_h;
        }

        public void setFace_h(int face_h) {
            this.face_h = face_h;
        }

    }


    /**
     * "abstract": "赵丽颖，1987年10月16日出生于河北省廊坊市，中国内地影视女演员。2006年，因获得雅虎搜星比赛冯小刚组冠军而进入演艺圈；同
     * 年，在冯小刚执导的广告片《跪族篇》中担任女主角。2011年，因在古装剧《新还珠格格》中饰演晴儿一角而被观众认识。2013年，凭借古装剧《陆贞
     * 传奇》获得更多关注。2014年10月，在第10届金鹰电视艺术节举办的投票活动中被选为“金鹰女神”；12月，凭借都市爱情剧《杉杉来了》获得第5届国
     * 剧盛典内地最具人气女演员奖；同年，成立海润传媒赵丽颖工作室。2015年，主演的仙侠剧《花千骨》打破中国内地周播剧收视纪录，而其个人则凭借该
     * 剧先后获得第6届澳门国际电视节金莲花最佳女主角奖、第6届国剧盛典最具收视号召力演员奖、第22届上海电视节白玉兰奖最佳女主角奖提名、第28届
     * 中国电视金鹰奖观众喜爱的女演员奖。2017年，凭借电影《乘风破浪》获得第24届北京大学生电影节最受大学生欢迎女演员奖；同年，其主演的古装剧
     * 《楚乔传》成为中国内地首部在播期间网络播放量突破400亿次的电视剧。",
     * "birth": "1987年10月16日",
     * "sub_ne": "赵丽颖",
     * "xingzuo": "天秤座",
     * "gender": "female",
     * "minzu": "汉族",
     * "st": "http://t10.baidu.com/it/u=661626891,1438464912&fm=87",
     * "xuexing": "A型",
     * "zp": "陆贞传奇、胭脂、杉杉来了、花千骨、老九门、青云志、追鱼传奇、楚乔传",
     * "gj": "中国",
     * "bdbkNewlemmaId": "10075976",
     * "category_tag": "娱乐人物\", \"人物\", \"歌手\", \"演员",
     * "birthplace": "河北廊坊",
     * "bdbkModulesInfo": we don't need it,
     * "zy": "演员",
     * "height": "165cm",
     * "url": "http://baike.baidu.com/item/%E8%B5%B5%E4%B8%BD%E9%A2%96/10075976",
     * "desc_label": "赵丽颖",
     * "ne": "赵丽颖",
     * "weight": "43kg"
          */
    public static class FurtherInner {

        @SerializedName(value = "abstract")
        private String description;
        @SerializedName(value = "url")
        private String linkUrl;

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
    }
}

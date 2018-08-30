package com.tinno.aimap.model;

import java.util.List;

public class TestResultBean {

    /**
     * result : {"result_page":"http://graph.baidu.com/s?srcp=&tn=wise&idctag=nj&sids=10010_10125_10028_10003_10004_10103_10201_10040_10070_10062_10080_10191_10290_10390_10490_10691_10700&logid=2788164181&entrance=&output_verticals=general&sign=7379ef9f4d1a79a6b0848x1535337988&client_app_id=10487822","scan":[{"map":"http://api.map.baidu.com/staticimage/v2?ak=04fb6af1b74037954784b365c9af9f6d&center=116.41731297525,39.887977077342&width=320&height=160&zoom=13&markers=116.41731297525,39.887977077342&markerStyles=L,&copyright=1","openTime":"1. 四大门： \n开门时间：\n旺季（4月~10月）：06:00  \n淡季（11月~次年3月）：06:30\n止售时间：20:00  \n静园时间：21:00\n关门时间：22:00\n2. 景点\n开门时间：08:00\n旺季（4月~10月）关门时间：17:30（大门16:00止售联票）  \n淡季（11月~次年3月）关门时间：17:00（大门15:30止售联票）","name":"天坛","price":"旺季（4月~10月）：15.00元\n淡季（11月~次年3月）：10.00元\n联票（含大门票、神乐署、斋宫、祈年殿、圜丘、回音壁）：旺季35.00元；淡季30.00元","longitude":"116.41731297525","jd_mapUrl":"http://map.baidu.com/mobile/webapp/search/search/qt=inf&uid=45ef9a2b5508cbfda2d230e6/%3Fthird_party=webapp-aladdin&ala_item=list&ala_tpl=poi_multiple_rich&vt=map&traffic=on","latitude":"39.887977077342","desc":"天坛公园，在北京市东城区永定门内大街东侧。中国现存最大的古代祭祀性建筑群。入选\u201c世界文化遗产\u201d名录。\n天坛始建于明永乐十八年（1420年），为明、清两代帝王祭祀皇天、祈五谷丰登之场所。\n天坛是圜丘、祈谷两坛的总称，有坛墙两重，形成内外坛。\n主要建筑在内坛，圜丘坛在南、祈谷坛在北，二坛同在一条南北轴线上。\n圜丘坛内主要建筑有圜丘坛、皇穹宇等等，祈谷坛内主要建筑有祈年殿、皇乾殿、祈年门等。著名的祈年殿在最北方，这是天坛内最宏伟的建筑，也是想象中离天最近的地方。\n天坛的建筑不仅具有独特的艺术风格，而且有些建筑还巧妙地运用了力学、声学、几何学原理，因此具有重要价值。\n天坛以严谨的建筑布局、奇特的建筑构造和瑰丽的建筑装饰著称于世。总占地面积约 270万平方米。主要建筑物在内坛，南有圜丘坛、皇穹宇，北有祈年殿、皇乾殿，由一条贯通南北的甬道－－丹陛桥，把这二组建筑连接起来。外坛古柏苍郁，环绕着内坛，使主要建筑群显得更加庄严宏伟。\n圜丘坛是举行冬至祭天大典的场所，主要建筑有圜丘、皇穹宇及配殿、神厨、三库及宰牲亭，附属建筑有具服台、望灯等。\n皇穹宇院落位于圜丘坛外壝北侧，坐北朝南，圆形围墙，南面设三座琉璃门，主要建筑有皇穹宇和东西配殿，是供奉圜丘坛祭祀神位的场所。皇穹宇殿前甬路从北面数， 前三块石板即为\u201c三音石\u201d。当站在第一块石板上击一下掌， 只能听见一声回音； 当站在第二块石板上击一下掌就可以听见两声回音；当站在第三块石板上击一下掌便听到连续不断的三声回音。\n在皇穹宇的四周有一道厚约0.9米的围墙，站在一端贴着墙小声说话，站在另一端的人只要耳贴墙面就能听得异常清晰，并且还有立体声效果，这就是\u201c回音壁\u201d。这证明500年前的中国人已经能够运用声学原理。\n斋宫是皇帝举行祭天大典前进行斋戒的场所，位于祈谷坛内坛西南隅。宫内建有无梁殿、寝殿、钟楼、值守房和巡守步廊等礼仪、居住、服务、警卫专用建筑，均采用绿色琉璃瓦，以两重宫墙、两道御沟围护。斋宫布局严谨，环境典雅，是中国古代祭祀斋戒建筑的代表作。","linkUrl":"http://m.baidu.com/sf?openapi=1&dspName=iphone&from_sf=1&pd=jingdian_detail&resource_id=4239&word=%E5%A4%A9%E5%9D%9B","imgSfUrl":"https://m.baidu.com/sf?word=%E5%A4%A9%E5%9D%9B&mod=0&tn=normal&pd=jingdian_comment&actname=common_img_view&resource_id=10306","thumbUrl":"http://t12.baidu.com/it/u=2100798038,645189243&fm=87","objurl":"http://img3.imgtn.bdimg.com/it/u=478879270,3973731513&fm=23&gp=0.jpg","score":25.285596847534}]}
     * error_code : 0
     * error_msg : succ
     * support : 百度识图提供技术支持
     * logid : 2788164181
     */

    private ResultBean result;
    private int error_code;
    private String error_msg;
    private String support;
    private String logid;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public static class ResultBean {
        /**
         * result_page : http://graph.baidu.com/s?srcp=&tn=wise&idctag=nj&sids=10010_10125_10028_10003_10004_10103_10201_10040_10070_10062_10080_10191_10290_10390_10490_10691_10700&logid=2788164181&entrance=&output_verticals=general&sign=7379ef9f4d1a79a6b0848x1535337988&client_app_id=10487822
         * scan : [{"map":"http://api.map.baidu.com/staticimage/v2?ak=04fb6af1b74037954784b365c9af9f6d&center=116.41731297525,39.887977077342&width=320&height=160&zoom=13&markers=116.41731297525,39.887977077342&markerStyles=L,&copyright=1","openTime":"1. 四大门： \n开门时间：\n旺季（4月~10月）：06:00  \n淡季（11月~次年3月）：06:30\n止售时间：20:00  \n静园时间：21:00\n关门时间：22:00\n2. 景点\n开门时间：08:00\n旺季（4月~10月）关门时间：17:30（大门16:00止售联票）  \n淡季（11月~次年3月）关门时间：17:00（大门15:30止售联票）","name":"天坛","price":"旺季（4月~10月）：15.00元\n淡季（11月~次年3月）：10.00元\n联票（含大门票、神乐署、斋宫、祈年殿、圜丘、回音壁）：旺季35.00元；淡季30.00元","longitude":"116.41731297525","jd_mapUrl":"http://map.baidu.com/mobile/webapp/search/search/qt=inf&uid=45ef9a2b5508cbfda2d230e6/%3Fthird_party=webapp-aladdin&ala_item=list&ala_tpl=poi_multiple_rich&vt=map&traffic=on","latitude":"39.887977077342","desc":"天坛公园，在北京市东城区永定门内大街东侧。中国现存最大的古代祭祀性建筑群。入选\u201c世界文化遗产\u201d名录。\n天坛始建于明永乐十八年（1420年），为明、清两代帝王祭祀皇天、祈五谷丰登之场所。\n天坛是圜丘、祈谷两坛的总称，有坛墙两重，形成内外坛。\n主要建筑在内坛，圜丘坛在南、祈谷坛在北，二坛同在一条南北轴线上。\n圜丘坛内主要建筑有圜丘坛、皇穹宇等等，祈谷坛内主要建筑有祈年殿、皇乾殿、祈年门等。著名的祈年殿在最北方，这是天坛内最宏伟的建筑，也是想象中离天最近的地方。\n天坛的建筑不仅具有独特的艺术风格，而且有些建筑还巧妙地运用了力学、声学、几何学原理，因此具有重要价值。\n天坛以严谨的建筑布局、奇特的建筑构造和瑰丽的建筑装饰著称于世。总占地面积约 270万平方米。主要建筑物在内坛，南有圜丘坛、皇穹宇，北有祈年殿、皇乾殿，由一条贯通南北的甬道－－丹陛桥，把这二组建筑连接起来。外坛古柏苍郁，环绕着内坛，使主要建筑群显得更加庄严宏伟。\n圜丘坛是举行冬至祭天大典的场所，主要建筑有圜丘、皇穹宇及配殿、神厨、三库及宰牲亭，附属建筑有具服台、望灯等。\n皇穹宇院落位于圜丘坛外壝北侧，坐北朝南，圆形围墙，南面设三座琉璃门，主要建筑有皇穹宇和东西配殿，是供奉圜丘坛祭祀神位的场所。皇穹宇殿前甬路从北面数， 前三块石板即为\u201c三音石\u201d。当站在第一块石板上击一下掌， 只能听见一声回音； 当站在第二块石板上击一下掌就可以听见两声回音；当站在第三块石板上击一下掌便听到连续不断的三声回音。\n在皇穹宇的四周有一道厚约0.9米的围墙，站在一端贴着墙小声说话，站在另一端的人只要耳贴墙面就能听得异常清晰，并且还有立体声效果，这就是\u201c回音壁\u201d。这证明500年前的中国人已经能够运用声学原理。\n斋宫是皇帝举行祭天大典前进行斋戒的场所，位于祈谷坛内坛西南隅。宫内建有无梁殿、寝殿、钟楼、值守房和巡守步廊等礼仪、居住、服务、警卫专用建筑，均采用绿色琉璃瓦，以两重宫墙、两道御沟围护。斋宫布局严谨，环境典雅，是中国古代祭祀斋戒建筑的代表作。","linkUrl":"http://m.baidu.com/sf?openapi=1&dspName=iphone&from_sf=1&pd=jingdian_detail&resource_id=4239&word=%E5%A4%A9%E5%9D%9B","imgSfUrl":"https://m.baidu.com/sf?word=%E5%A4%A9%E5%9D%9B&mod=0&tn=normal&pd=jingdian_comment&actname=common_img_view&resource_id=10306","thumbUrl":"http://t12.baidu.com/it/u=2100798038,645189243&fm=87","objurl":"http://img3.imgtn.bdimg.com/it/u=478879270,3973731513&fm=23&gp=0.jpg","score":25.285596847534}]
         */

        private String result_page;
        private List<ScanBean> scan;

        public String getResult_page() {
            return result_page;
        }

        public void setResult_page(String result_page) {
            this.result_page = result_page;
        }

        public List<ScanBean> getScan() {
            return scan;
        }

        public void setScan(List<ScanBean> scan) {
            this.scan = scan;
        }

        public static class ScanBean {
            /**
             * map : http://api.map.baidu.com/staticimage/v2?ak=04fb6af1b74037954784b365c9af9f6d&center=116.41731297525,39.887977077342&width=320&height=160&zoom=13&markers=116.41731297525,39.887977077342&markerStyles=L,&copyright=1
             * openTime : 1. 四大门：
             开门时间：
             旺季（4月~10月）：06:00
             淡季（11月~次年3月）：06:30
             止售时间：20:00
             静园时间：21:00
             关门时间：22:00
             2. 景点
             开门时间：08:00
             旺季（4月~10月）关门时间：17:30（大门16:00止售联票）
             淡季（11月~次年3月）关门时间：17:00（大门15:30止售联票）
             * name : 天坛
             * price : 旺季（4月~10月）：15.00元
             淡季（11月~次年3月）：10.00元
             联票（含大门票、神乐署、斋宫、祈年殿、圜丘、回音壁）：旺季35.00元；淡季30.00元
             * longitude : 116.41731297525
             * jd_mapUrl : http://map.baidu.com/mobile/webapp/search/search/qt=inf&uid=45ef9a2b5508cbfda2d230e6/%3Fthird_party=webapp-aladdin&ala_item=list&ala_tpl=poi_multiple_rich&vt=map&traffic=on
             * latitude : 39.887977077342
             * desc : 天坛公园，在北京市东城区永定门内大街东侧。中国现存最大的古代祭祀性建筑群。入选“世界文化遗产”名录。
             天坛始建于明永乐十八年（1420年），为明、清两代帝王祭祀皇天、祈五谷丰登之场所。
             天坛是圜丘、祈谷两坛的总称，有坛墙两重，形成内外坛。
             主要建筑在内坛，圜丘坛在南、祈谷坛在北，二坛同在一条南北轴线上。
             圜丘坛内主要建筑有圜丘坛、皇穹宇等等，祈谷坛内主要建筑有祈年殿、皇乾殿、祈年门等。著名的祈年殿在最北方，这是天坛内最宏伟的建筑，也是想象中离天最近的地方。
             天坛的建筑不仅具有独特的艺术风格，而且有些建筑还巧妙地运用了力学、声学、几何学原理，因此具有重要价值。
             天坛以严谨的建筑布局、奇特的建筑构造和瑰丽的建筑装饰著称于世。总占地面积约 270万平方米。主要建筑物在内坛，南有圜丘坛、皇穹宇，北有祈年殿、皇乾殿，由一条贯通南北的甬道－－丹陛桥，把这二组建筑连接起来。外坛古柏苍郁，环绕着内坛，使主要建筑群显得更加庄严宏伟。
             圜丘坛是举行冬至祭天大典的场所，主要建筑有圜丘、皇穹宇及配殿、神厨、三库及宰牲亭，附属建筑有具服台、望灯等。
             皇穹宇院落位于圜丘坛外壝北侧，坐北朝南，圆形围墙，南面设三座琉璃门，主要建筑有皇穹宇和东西配殿，是供奉圜丘坛祭祀神位的场所。皇穹宇殿前甬路从北面数， 前三块石板即为“三音石”。当站在第一块石板上击一下掌， 只能听见一声回音； 当站在第二块石板上击一下掌就可以听见两声回音；当站在第三块石板上击一下掌便听到连续不断的三声回音。
             在皇穹宇的四周有一道厚约0.9米的围墙，站在一端贴着墙小声说话，站在另一端的人只要耳贴墙面就能听得异常清晰，并且还有立体声效果，这就是“回音壁”。这证明500年前的中国人已经能够运用声学原理。
             斋宫是皇帝举行祭天大典前进行斋戒的场所，位于祈谷坛内坛西南隅。宫内建有无梁殿、寝殿、钟楼、值守房和巡守步廊等礼仪、居住、服务、警卫专用建筑，均采用绿色琉璃瓦，以两重宫墙、两道御沟围护。斋宫布局严谨，环境典雅，是中国古代祭祀斋戒建筑的代表作。
             * linkUrl : http://m.baidu.com/sf?openapi=1&dspName=iphone&from_sf=1&pd=jingdian_detail&resource_id=4239&word=%E5%A4%A9%E5%9D%9B
             * imgSfUrl : https://m.baidu.com/sf?word=%E5%A4%A9%E5%9D%9B&mod=0&tn=normal&pd=jingdian_comment&actname=common_img_view&resource_id=10306
             * thumbUrl : http://t12.baidu.com/it/u=2100798038,645189243&fm=87
             * objurl : http://img3.imgtn.bdimg.com/it/u=478879270,3973731513&fm=23&gp=0.jpg
             * score : 25.285596847534
             */

            private String map;
            private String openTime;
            private String name;
            private String price;
            private String longitude;
            private String jd_mapUrl;
            private String latitude;
            private String desc;
            private String linkurl;
            private String imgSfUrl;
            private String thumburl;
            private String objurl;
            private double score;

            public String getMap() {
                return map;
            }

            public void setMap(String map) {
                this.map = map;
            }

            public String getOpenTime() {
                return openTime;
            }

            public void setOpenTime(String openTime) {
                this.openTime = openTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getJd_mapUrl() {
                return jd_mapUrl;
            }

            public void setJd_mapUrl(String jd_mapUrl) {
                this.jd_mapUrl = jd_mapUrl;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getLinkurl() {
                return linkurl;
            }

            public void setLinkurl(String linkurl) {
                this.linkurl = linkurl;
            }

            public String getImgSfUrl() {
                return imgSfUrl;
            }

            public void setImgSfUrl(String imgSfUrl) {
                this.imgSfUrl = imgSfUrl;
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
        }
    }
}

package com.tinno.aimap.model;

import java.util.List;

public abstract class ObjectBean {

    /**
     * baike : [{"value":"{\"pic_1\":\"http:\\/\\/t11.baidu.com\\/it\\/u=2703861760,2381598700&fm=58&w=121&h=91&img.JPEG&bpow=1079&bpoh=1438\",\"key\":\"哈士奇犬\",\"baike_url\":\"http:\\/\\/baike.baidu.com\\/item\\/%E8%A5%BF%E4%BC%AF%E5%88%A9%E4%BA%9A%E9%9B%AA%E6%A9%87%E7%8A%AC\\/540855\",\"abstract\":\"西伯利亚雪橇犬（俄语：Сибирский хаски，英语：Siberian husky），常见别名哈士奇，昵称为二哈。西伯利亚雪橇犬体重介于雄犬20-27公斤，雌犬16-23公斤，身高大约雄犬肩高53-58厘米，雌犬51-56厘米，是一种中型犬。西伯利亚雪橇犬是原始的古老犬种，在西伯利亚东北部、格陵兰南部生活。哈士奇名字的由来，是源自其独特的嘶哑声。哈士奇性格多变，有的极端胆小，也有的极端暴力，进入大陆和家庭的哈士奇，都已经没有了这种极端的性格，比较温顺，是一种流行于全球的宠物犬。与金毛犬、拉布拉多并列为三大无攻击型犬类。被世界各地广泛饲养，并在全球范围内，有大量该犬种的赛事。\",\"pic\":\"http:\\/\\/f.hiphotos.baidu.com\\/baike\\/pic\\/item\\/58ee3d6d55fbb2fb4e4573f6444a20a44723dce3.jpg\",\"pic_2\":\"http:\\/\\/t10.baidu.com\\/it\\/u=3036203928,2873901048&fm=58&w=121&h=121&img.JPEG&bpow=1079&bpoh=1438\",\"pic_3\":\"http:\\/\\/t12.baidu.com\\/it\\/u=3030954164,2873807303&fm=58&w=121&h=140&img.JPEG&bpow=1079&bpoh=1438\",\"card_kv\":[{\"value\":\"西伯利亚雪橇犬\",\"key\":\"中文学名\"},{\"value\":\"Canis lupus familiaris\",\"key\":\"拉丁学名\"},{\"value\":\"哈士奇、二哈、撒手没、拆迁办主任\",\"key\":\"别称\"},{\"value\":\"动物界\",\"key\":\"界\"},{\"value\":\"脊索动物门\",\"key\":\"门\"},{\"value\":\"脊椎动物亚门\",\"key\":\"亚门\"},{\"value\":\"哺乳纲\",\"key\":\"纲\"},{\"value\":\"真兽亚纲\",\"key\":\"亚纲\"},{\"value\":\"食肉目\",\"key\":\"目\"},{\"value\":\"裂脚亚目\",\"key\":\"亚目\"},{\"value\":\"犬科\",\"key\":\"科\"},{\"value\":\"犬亚科\",\"key\":\"亚科\"},{\"value\":\"犬\",\"key\":\"族\"},{\"value\":\"犬属\",\"key\":\"属\"},{\"value\":\"犬属\",\"key\":\"亚属\"},{\"value\":\"灰狼\",\"key\":\"种\"},{\"value\":\"家犬\",\"key\":\"亚种\"},{\"value\":\"西伯利亚东北部，格陵兰南部\",\"key\":\"分布区域\"},{\"value\":\"俄罗斯西伯利亚地区\",\"key\":\"原产地\"},{\"value\":\"雪地雪橇犬\",\"key\":\"原始用途\"},{\"value\":\"伴侣犬、雪橇竞赛犬\",\"key\":\"今日用途\"},{\"value\":\"中型犬\",\"key\":\"体型\"},{\"value\":\"狗粮\",\"key\":\"适合食物\"},{\"value\":\"SIBERIAN HUSKY\",\"key\":\"英文名称\"},{\"value\":\"Сибирский хаски\",\"key\":\"俄语名称\"},{\"value\":\"10年以上\",\"key\":\"寿命\"},{\"value\":\"雄犬肩高53-58CM，雌犬51-56CM\",\"key\":\"体高\"},{\"value\":\"雄犬20-27公斤,雌犬16-23公斤\",\"key\":\"体重\"},{\"value\":\"45名\",\"key\":\"智商排名\"}]}","keyword":"哈士奇犬","score":0.93928}]
     * words : 哈士奇犬
     */

    protected String words;

    protected List<BaikeBean> baike;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    protected List<BaikeBean> getBaike() {
        return baike;
    }

    protected void setBaike(List<BaikeBean> baike) {
        this.baike = baike;
    }

    public static class BaikeBean {
        /**
         * value : {"pic_1":"http:\/\/t11.baidu.com\/it\/u=2703861760,2381598700&fm=58&w=121&h=91&img.JPEG&bpow=1079&bpoh=1438","key":"哈士奇犬","baike_url":"http:\/\/baike.baidu.com\/item\/%E8%A5%BF%E4%BC%AF%E5%88%A9%E4%BA%9A%E9%9B%AA%E6%A9%87%E7%8A%AC\/540855","abstract":"西伯利亚雪橇犬（俄语：Сибирский хаски，英语：Siberian husky），常见别名哈士奇，昵称为二哈。西伯利亚雪橇犬体重介于雄犬20-27公斤，雌犬16-23公斤，身高大约雄犬肩高53-58厘米，雌犬51-56厘米，是一种中型犬。西伯利亚雪橇犬是原始的古老犬种，在西伯利亚东北部、格陵兰南部生活。哈士奇名字的由来，是源自其独特的嘶哑声。哈士奇性格多变，有的极端胆小，也有的极端暴力，进入大陆和家庭的哈士奇，都已经没有了这种极端的性格，比较温顺，是一种流行于全球的宠物犬。与金毛犬、拉布拉多并列为三大无攻击型犬类。被世界各地广泛饲养，并在全球范围内，有大量该犬种的赛事。","pic":"http:\/\/f.hiphotos.baidu.com\/baike\/pic\/item\/58ee3d6d55fbb2fb4e4573f6444a20a44723dce3.jpg","pic_2":"http:\/\/t10.baidu.com\/it\/u=3036203928,2873901048&fm=58&w=121&h=121&img.JPEG&bpow=1079&bpoh=1438","pic_3":"http:\/\/t12.baidu.com\/it\/u=3030954164,2873807303&fm=58&w=121&h=140&img.JPEG&bpow=1079&bpoh=1438","card_kv":[{"value":"西伯利亚雪橇犬","key":"中文学名"},{"value":"Canis lupus familiaris","key":"拉丁学名"},{"value":"哈士奇、二哈、撒手没、拆迁办主任","key":"别称"},{"value":"动物界","key":"界"},{"value":"脊索动物门","key":"门"},{"value":"脊椎动物亚门","key":"亚门"},{"value":"哺乳纲","key":"纲"},{"value":"真兽亚纲","key":"亚纲"},{"value":"食肉目","key":"目"},{"value":"裂脚亚目","key":"亚目"},{"value":"犬科","key":"科"},{"value":"犬亚科","key":"亚科"},{"value":"犬","key":"族"},{"value":"犬属","key":"属"},{"value":"犬属","key":"亚属"},{"value":"灰狼","key":"种"},{"value":"家犬","key":"亚种"},{"value":"西伯利亚东北部，格陵兰南部","key":"分布区域"},{"value":"俄罗斯西伯利亚地区","key":"原产地"},{"value":"雪地雪橇犬","key":"原始用途"},{"value":"伴侣犬、雪橇竞赛犬","key":"今日用途"},{"value":"中型犬","key":"体型"},{"value":"狗粮","key":"适合食物"},{"value":"SIBERIAN HUSKY","key":"英文名称"},{"value":"Сибирский хаски","key":"俄语名称"},{"value":"10年以上","key":"寿命"},{"value":"雄犬肩高53-58CM，雌犬51-56CM","key":"体高"},{"value":"雄犬20-27公斤,雌犬16-23公斤","key":"体重"},{"value":"45名","key":"智商排名"}]}
         * keyword : 哈士奇犬
         * score : 0.93928
         */

        private String value;
        private String keyword;
        private double score;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "BaikeBean{" +
                    "value='" + value + '\'' +
                    ", keyword='" + keyword + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}

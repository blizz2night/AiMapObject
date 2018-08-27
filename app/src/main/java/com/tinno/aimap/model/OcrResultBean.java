package com.tinno.aimap.model;

public class OcrResultBean {

    /**
     * result : {"words":" 1.2 Fragmentstatepageradapter 5 Fragmentpageradapter\"\u20261841 11.31.深入 A-J学习:: Viewpager的 IYERF工作原理\u2026..11868611.4深入学习:以代码的方式创建视图\u20261871 11.51.5挑 #战练习::恢&复 Crimefragment的 y边距\u2026\u20261871 111..6挑战练习::添加 tu Jump to First按 EI钮和 Fl Jump to Last按钮 th.\u2026\u2026188"}
     * error_code : 0
     * error_msg : succ
     * support : 百度识图提供技术支持
     * logid : 1355213409
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
         * words :  1.2 Fragmentstatepageradapter 5 Fragmentpageradapter"…1841 11.31.深入 A-J学习:: Viewpager的 IYERF工作原理…..11868611.4深入学习:以代码的方式创建视图…1871 11.51.5挑 #战练习::恢&复 Crimefragment的 y边距……1871 111..6挑战练习::添加 tu Jump to First按 EI钮和 Fl Jump to Last按钮 th.……188
         */

        private String words;

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }
    }
}

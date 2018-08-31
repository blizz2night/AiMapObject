package com.tinno.aimap.model;

public class OcrResponseBean extends ResponseBean {

    /**
     * result : {"words":" 1.2 Fragmentstatepageradapter 5 Fragmentpageradapter\"\u20261841 11.31.深入 A-J学习:: Viewpager的 IYERF工作原理\u2026..11868611.4深入学习:以代码的方式创建视图\u20261871 11.51.5挑 #战练习::恢&复 Crimefragment的 y边距\u2026\u20261871 111..6挑战练习::添加 tu Jump to First按 EI钮和 Fl Jump to Last按钮 th.\u2026\u2026188"}
     * error_code : 0
     * error_msg : succ
     * support : 百度识图提供技术支持
     * logid : 1355213409
     */

    private Result result;
    private int errorCode;
    private String errorMsg;
    private String support;
    private String logid;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
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

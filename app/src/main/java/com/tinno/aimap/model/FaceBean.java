package com.tinno.aimap.model;

import java.util.Map;

public class FaceBean {
    private String words;
    private Map<String, Object> baike;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Map<String, Object> getBaike() {
        return baike;
    }

    public void setBaike(Map<String, Object> baike) {
        this.baike = baike;
    }
}

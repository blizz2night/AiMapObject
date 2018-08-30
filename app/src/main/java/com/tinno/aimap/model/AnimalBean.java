package com.tinno.aimap.model;

public class AnimalBean extends ComplexJsonObjBean {

    public AnimalBean(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "AnimalBean{" +
                "name='" + name + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

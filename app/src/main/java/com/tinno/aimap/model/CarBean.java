package com.tinno.aimap.model;

public class CarBean extends ComplexJsonObjBean {

    public CarBean(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "CarBean{" +
                "name='" + name + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

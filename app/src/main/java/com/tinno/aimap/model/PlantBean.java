package com.tinno.aimap.model;

public class PlantBean extends ComplexJsonObjBean {
    public PlantBean(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "PlantBean{" +
                "name='" + name + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

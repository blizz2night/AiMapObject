package com.tinno.aimap.model;

public class DishesBean extends ComplexJsonObjBean {

    public DishesBean(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "DishesBean{" +
                "name='" + name + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

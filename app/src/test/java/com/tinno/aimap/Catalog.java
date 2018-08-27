package com.tinno.aimap;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({Catalog.ANIMAL, Catalog.BOOK, Catalog.BRAND,
        Catalog.CAR, Catalog.DISH, Catalog.FACE, Catalog.MOVIE,
        Catalog.MUSIC, Catalog.PLANT, Catalog.PRODUCT,
        Catalog.SCAN, Catalog.OCR})
public @interface Catalog {
    public static final String ANIMAL = "animal";
    public static final String BOOK = "book";
    public static final String BRAND = "brand";
    public static final String CAR = "car";
    public static final String DISH = "dish";
    public static final String FACE = "face";
    public static final String MOVIE = "movie";
    public static final String MUSIC = "music";
    public static final String PLANT = "plant";
    public static final String PRODUCT = "product";
    public static final String SCAN = "scan";

    public static final String OCR = "ocr";
}

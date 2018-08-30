package com.tinno.aimap.service;

import android.support.annotation.StringDef;

@StringDef({RequestKey.ACCESS_TOKEN, RequestKey.CLIENT_ID,
        RequestKey.CLIENT_SECRET, RequestKey.GRANT_TYPE,
        RequestKey.IMAGE, RequestKey.ECOLOGY_UID, RequestKey.ECO})
public @interface RequestKey {
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String GRANT_TYPE = "grant_type";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String IMAGE = "image";
    public static final String ECOLOGY_UID = "ecology_uid";
    public static final String ECO = "eco";
}

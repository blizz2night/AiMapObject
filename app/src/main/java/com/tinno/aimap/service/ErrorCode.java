package com.tinno.aimap.service;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ErrorCode.ACCESS_TOKEN_EXPIRED,ErrorCode.SUCCESS})
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorCode {
    int ACCESS_TOKEN_EXPIRED = 111;
    int SUCCESS = 0;
    int NO_RESPONSE_RETURN = 328008;
}

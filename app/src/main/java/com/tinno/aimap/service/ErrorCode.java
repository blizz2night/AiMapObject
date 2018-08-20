package com.tinno.aimap.service;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

@IntDef({ErrorCode.ACCESS_TOKEN_EXPIRED,ErrorCode.SUCCESS})
@Retention(RetentionPolicy.RUNTIME)
@Target({PARAMETER,METHOD,FIELD})
public @interface ErrorCode {
    int ACCESS_TOKEN_EXPIRED = 111;
    int SUCCESS = 0;
}

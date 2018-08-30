package com.tinno.aimap.service;

import android.support.annotation.IntDef;

@IntDef({ErrorCode.ACCESS_TOKEN_EXPIRED, ErrorCode.SUCCESS,
        ErrorCode.NO_RESPONSE_RETURN, ErrorCode.NO_PERMISSION})
public @interface ErrorCode {
    int ACCESS_TOKEN_EXPIRED = 111;
    int SUCCESS = 0;
    int NO_RESPONSE_RETURN = 328008;
    int NO_PERMISSION = 6;
}

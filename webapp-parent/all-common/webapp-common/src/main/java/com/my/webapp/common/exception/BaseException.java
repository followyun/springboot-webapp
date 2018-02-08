package com.my.webapp.common.exception;


import com.my.webapp.common.constants.BaseErrorCode;

/**
 * 基本异常
 */
public abstract class BaseException extends Exception{
    private BaseErrorCode baseErrorCode;

    public BaseException(BaseErrorCode baseErrorCode) {
        this.baseErrorCode = baseErrorCode;
    }

    public BaseErrorCode getBaseErrorCode() {
        return baseErrorCode;
    }
}

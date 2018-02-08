package com.my.webapp.common.exception;


import com.my.webapp.common.constants.BaseErrorCode;

/**
 * 业务异常
 */
public class BusinessException extends BaseException{
    public BusinessException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}

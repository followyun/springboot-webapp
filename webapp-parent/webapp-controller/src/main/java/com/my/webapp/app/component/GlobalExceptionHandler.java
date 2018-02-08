package com.my.webapp.app.component;

import com.my.webapp.app.controller.response.Response;
import com.my.webapp.common.constants.ErrorCode;
import com.my.webapp.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response errorHandler(HttpServletRequest req, Exception e){
        if( e instanceof BusinessException){
            logger.error("errorMsg: "+((BusinessException) e).getBaseErrorCode().getErrorMsg()+";"+"debugMsg: "+((BusinessException) e).getBaseErrorCode().getDebugMsg(), e);
            return Response.fail(((BusinessException) e).getBaseErrorCode().getCode(), ((BusinessException) e).getBaseErrorCode().getErrorMsg(), ((BusinessException) e).getBaseErrorCode().getDebugMsg());
        }else if(e instanceof MethodArgumentNotValidException){
            logger.error("参数错误", e);
            return Response.fail(ErrorCode.INVALID_PARAMTER.getCode(), ErrorCode.INVALID_PARAMTER.getErrorMsg(), "参数错误");

        }else if(e instanceof HttpRequestMethodNotSupportedException){
            logger.error("不可用的请求方法", e);
            return Response.fail(ErrorCode.HTTP_METHOD_NOT_ALLOWED.getCode(), ErrorCode.HTTP_METHOD_NOT_ALLOWED.getErrorMsg(), "不可用的请求方法");

        }
        else{
            logger.error("其它异常：", e);
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getErrorMsg(), null);
        }
    }
}

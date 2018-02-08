package com.my.webapp.common.constants;

/**
 */
public enum  ErrorCode implements BaseErrorCode{
    SYSTEM_ERROR(-1, "系统错误"),
    HTTP_METHOD_NOT_ALLOWED(2, "请求方法不允许"),
    INVALID_PARAMTER(3, "参数错误"),
    PSSWORD_ERROR(4, "密码错误"),
    ;
    private Integer code;
    private String errorMsg;
    private String debugMsg;

     ErrorCode(Integer code, String errorMsg, String debugMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.debugMsg = debugMsg;
    }
    ErrorCode(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.debugMsg = null;
    }

    public Integer getCode() {
        return code;
    }



    public String getErrorMsg() {
        return errorMsg;
    }


    public String getDebugMsg() {
        return debugMsg;
    }

    public BaseErrorCode formatMsg(String errorMsg){
        return new FormatErrorCode(this.getCode(), errorMsg, null);
    }
    public BaseErrorCode formatMsg(String errorMsg, String debugMsg){
        return new FormatErrorCode(this.getCode(), errorMsg, debugMsg);
    }

    private class FormatErrorCode implements BaseErrorCode{
        private Integer code;
        private String errorMsg;
        private String debugMsg;

        private FormatErrorCode(Integer code, String errorMsg, String debugMsg) {
            this.code = code;
            this.errorMsg = errorMsg;
            this.debugMsg = debugMsg;
        }

        public Integer getCode() {
            return code;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public String getDebugMsg() {
            return debugMsg;
        }
    }
}

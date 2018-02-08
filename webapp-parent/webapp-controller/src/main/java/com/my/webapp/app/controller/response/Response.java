package com.my.webapp.app.controller.response;

/**
 */
public class Response<T> {

    /**
     * 响应状态（0 成功，-1 失败）
     */
    private Integer status;

    /**
     * 响应描述
     */
    private String msg;

    /**
     * 调试描述(方便调试)
     */
    private String debugMsg;
    /**
     * 响应内容
     */
    private T content;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDebugMsg() {
        return debugMsg;
    }

    public void setDebugMsg(String debugMsg) {
        this.debugMsg = debugMsg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    private enum ResponseStatus{
        SUCCESS(0),//成功
        FAIL(-1),//失败
        ;

        private Integer status;

        ResponseStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }
    }


    public static <T>Response<T>  success(T content){
        Response response = new Response<T>();
        response.setStatus(ResponseStatus.SUCCESS.getStatus());
        response.setContent(content);
        return response;
    }

    public static Response fail(Integer status, String errorMsg, String debugMsg){
        Response response = new Response();
        response.setStatus(status);
        response.setMsg(errorMsg);
        response.setDebugMsg(debugMsg);
        return response;
    }


}

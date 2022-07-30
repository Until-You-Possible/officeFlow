package com.example.flow.core;


/**
 *  统一返回结果～
 */

public class UnifyResponse {

    private final int code;
    private final String message;
    private final String request;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }

    public UnifyResponse(int code, String message, String request) {
        this.code = code;
        this.message = message;
        this.request = request;
    }

}

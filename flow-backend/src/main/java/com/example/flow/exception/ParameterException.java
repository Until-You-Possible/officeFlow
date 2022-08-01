package com.example.flow.exception;

public class ParameterException extends HttpException {

    public ParameterException(int code) {
        this.httpStatusCode = 400;
        this.code = code;

    }

}

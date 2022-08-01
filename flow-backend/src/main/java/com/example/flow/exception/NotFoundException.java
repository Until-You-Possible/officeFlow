package com.example.flow.exception;

public class NotFoundException extends HttpException {

    public NotFoundException(int code) {
        this.code = code;
        this.httpStatusCode = 404;
    }

}

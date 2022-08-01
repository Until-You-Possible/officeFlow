package com.example.flow.exception;

public class ServerErrorException extends HttpException {

    public ServerErrorException(int code) {
        this.code = code;
        this.httpStatusCode = 500;
    }

}


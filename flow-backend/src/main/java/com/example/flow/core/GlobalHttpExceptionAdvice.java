package com.example.flow.core;

import com.example.flow.core.configuration.ExceptionCodeConfiguration;
import com.example.flow.exception.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalHttpExceptionAdvice {

    private final ExceptionCodeConfiguration codeConfiguration;

    @Autowired
    public GlobalHttpExceptionAdvice(ExceptionCodeConfiguration codeConfiguration) {
        this.codeConfiguration = codeConfiguration;
    }

    public String getShowMessage(HttpException e) {
        return codeConfiguration.getMessage(e.getCode());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest request, Exception e) {
        String url = request.getRequestURI();
        String method = request.getMethod();
        return new UnifyResponse(9999, "server error", method + "" + url);
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req, HttpException e){
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        UnifyResponse message = new UnifyResponse(e.getCode(), this.getShowMessage(e), method + " " + requestUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        if (httpStatus == null) {
            return new ResponseEntity<>(message, headers, 500);
        }
        return new ResponseEntity<>(message, headers, httpStatus);
    }

}

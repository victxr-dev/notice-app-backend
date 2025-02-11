package com.noticias.notice.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends  RuntimeException{

    private HttpStatus httpStatus;
    private String message;

    public CustomException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public CustomException setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CustomException setMessage(String message) {
        this.message = message;
        return this;
    }
}

package com.noticias.notice.dto.exception;

public class MessageDto {
    private String message;

    public MessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public MessageDto setMessage(String message) {
        this.message = message;
        return this;
    }
}

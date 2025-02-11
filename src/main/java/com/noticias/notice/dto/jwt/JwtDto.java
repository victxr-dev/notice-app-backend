package com.noticias.notice.dto.jwt;

public class JwtDto {
    private String message;

    public JwtDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public JwtDto setMessage(String message) {
        this.message = message;
        return this;
    }
}

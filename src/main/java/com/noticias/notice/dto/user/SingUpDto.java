package com.noticias.notice.dto.user;

import jakarta.validation.constraints.NotBlank;

public class SingUpDto {

    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;

    public SingUpDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public @NotBlank(message = "email is required") String getEmail() {
        return email;
    }

    public SingUpDto setEmail(@NotBlank(message = "email is required") String email) {
        this.email = email;
        return this;
    }

    public @NotBlank(message = "password is required") String getPassword() {
        return password;
    }

    public SingUpDto setPassword(@NotBlank(message = "password is required") String password) {
        this.password = password;
        return this;
    }
}

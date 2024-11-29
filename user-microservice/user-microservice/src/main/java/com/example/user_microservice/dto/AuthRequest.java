package com.example.user_microservice.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class AuthRequest {

    @Email(message = "Адрес электронной почты должен быть валидным")
    @NotNull(message = "Требуется электронная почта")
    private String email;

    @NotNull(message = "Требуется ввести пароль")
    @Size(min = 6, message = "Пароль должна содержать не менее 6 символов")
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

package com.example.user_microservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserCreateRequest {

    @NotBlank(message = "Имя пользователя обязательно")
    private String username;

    private String gender;

    private String birthday; // Можно позже конвертировать в `LocalDate`

    @Email(message = "Невалидный email")
    @NotBlank(message = "Email обязателен")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;

//    @NotNull(message = "Роль обязательна")
//    private Long roleId;
}

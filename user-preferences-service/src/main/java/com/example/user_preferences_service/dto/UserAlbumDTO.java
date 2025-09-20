package com.example.user_preferences_service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserAlbumDTO {

    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Long userId;

    @NotNull(message = "Album ID cannot be null")
    @Min(value = 1, message = "Album ID must be greater than 0")
    private Long albumId;
}


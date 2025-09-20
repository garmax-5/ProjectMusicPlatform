package com.example.user_preferences_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserArtistDTO {
    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Long userId;
    @NotNull(message = "Artist ID cannot be null")
    @Min(value = 1, message = "Artist ID must be greater than 0")
    private Long artistId;
}

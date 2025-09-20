package com.example.user_preferences_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPlaylistDTO {
    private Long userId;
    private Long playlistId;
}


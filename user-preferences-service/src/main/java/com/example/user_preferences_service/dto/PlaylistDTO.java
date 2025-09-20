package com.example.user_preferences_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private Long id;
    private String name;
    private boolean isSystem;
    private Long userId;
}


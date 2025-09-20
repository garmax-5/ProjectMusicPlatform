package com.example.user_preferences_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTrackRequestDTO {
    private Long playlistId;
    private Long trackId;
    private Long userId;
}


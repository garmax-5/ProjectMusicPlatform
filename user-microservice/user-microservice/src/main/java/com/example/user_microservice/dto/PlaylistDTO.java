package com.example.user_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {
    private Long id;
    private String name;
    private boolean isSystem;
    private Long userId;
}

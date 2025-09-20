package com.example.user_preferences_service.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "playlists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Long id;

    @Column(name = "playlist_name", nullable = false, length = 100)
    private String name;

    @Column(name = "is_system", nullable = false)
    private boolean isSystem;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}


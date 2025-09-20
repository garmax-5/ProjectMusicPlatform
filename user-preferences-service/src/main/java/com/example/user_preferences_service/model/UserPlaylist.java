package com.example.user_preferences_service.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_playlists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "id")
public class UserPlaylist {
    @EmbeddedId
    private UserPlaylistId id;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class UserPlaylistId implements Serializable {
        private Long userId;
        private Long playlistId;
    }
}


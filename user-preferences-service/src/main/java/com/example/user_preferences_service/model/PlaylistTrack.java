package com.example.user_preferences_service.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "playlist_tracks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlaylistTrack {

    @EmbeddedId
    private PlaylistTrackId id;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class PlaylistTrackId implements Serializable {
        private Long playlistId;
        private Long trackId;
    }
}


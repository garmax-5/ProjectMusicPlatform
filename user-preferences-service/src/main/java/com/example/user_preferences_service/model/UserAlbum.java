package com.example.user_preferences_service.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_albums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "id")
public class UserAlbum {

    @EmbeddedId
    private UserAlbumId id;

    @Column(name = "added_date", nullable = false, updatable = false)
    private LocalDateTime addedDate = LocalDateTime.now();

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class UserAlbumId implements Serializable {
        private Long userId;
        private Long albumId;
    }
}


package com.example.user_preferences_service.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import lombok.*;

@Entity
@Table(name = "user_artists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserArtist {

    @EmbeddedId
    private UserArtistId id;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class UserArtistId implements Serializable {
        private Long userId;
        private Long artistId;
    }
}




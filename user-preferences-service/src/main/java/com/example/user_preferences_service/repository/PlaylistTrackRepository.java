package com.example.user_preferences_service.repository;

import com.example.user_preferences_service.model.PlaylistTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, PlaylistTrack.PlaylistTrackId> {
    List<PlaylistTrack> findByIdPlaylistId(Long playlistId);
}


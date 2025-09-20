package com.example.user_preferences_service.repository;

import com.example.user_preferences_service.model.UserPlaylist;
import com.example.user_preferences_service.model.UserPlaylist.UserPlaylistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlaylistRepository extends JpaRepository<UserPlaylist, UserPlaylistId> {
    List<UserPlaylist> findByIdUserId(Long userId);
    List<UserPlaylist> findByIdPlaylistId(Long playlistId);
}


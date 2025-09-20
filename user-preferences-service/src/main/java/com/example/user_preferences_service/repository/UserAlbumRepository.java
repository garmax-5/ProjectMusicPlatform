package com.example.user_preferences_service.repository;

import com.example.user_preferences_service.model.UserAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAlbumRepository extends JpaRepository<UserAlbum, UserAlbum.UserAlbumId> {
}



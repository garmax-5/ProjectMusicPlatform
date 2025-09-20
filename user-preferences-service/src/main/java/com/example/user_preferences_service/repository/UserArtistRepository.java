package com.example.user_preferences_service.repository;

import com.example.user_preferences_service.model.UserArtist;
import com.example.user_preferences_service.model.UserArtist.UserArtistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserArtistRepository extends JpaRepository<UserArtist, UserArtistId> {
}


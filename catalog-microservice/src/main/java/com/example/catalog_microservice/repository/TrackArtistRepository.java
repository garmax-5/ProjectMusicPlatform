package com.example.catalog_microservice.repository;


import com.example.catalog_microservice.model.Artist;
import com.example.catalog_microservice.model.Track;
import com.example.catalog_microservice.model.TrackArtist;
import com.example.catalog_microservice.model.TrackArtist.TrackArtistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackArtistRepository extends JpaRepository<TrackArtist, TrackArtist.TrackArtistId> {
    @Query("SELECT ta.artist FROM TrackArtist ta WHERE ta.track.id = :trackId")
    List<Artist> findArtistsByTrackId(@Param("trackId") Long trackId);

    @Query("SELECT ta.track FROM TrackArtist ta WHERE ta.artist.id = :artistId")
    List<Track> findTracksByArtistId(@Param("artistId") Long artistId);

    List<TrackArtist> findByTrack(Track track);

    Optional<TrackArtist> findByTrackId(Long trackId);

    Optional<TrackArtist> findFirstByTrackId(Long trackId);

}



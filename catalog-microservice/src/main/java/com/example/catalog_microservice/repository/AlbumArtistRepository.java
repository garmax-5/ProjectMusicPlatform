package com.example.catalog_microservice.repository;

import com.example.catalog_microservice.model.Album;
import com.example.catalog_microservice.model.AlbumArtist;
import com.example.catalog_microservice.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumArtistRepository extends JpaRepository<AlbumArtist, AlbumArtist.AlbumArtistId> {

    @Query("SELECT aa.artist FROM AlbumArtist aa WHERE aa.album.id = :albumId")
    List<Artist> findArtistsByAlbumId(@Param("albumId") Long albumId);

    @Query("SELECT aa.album FROM AlbumArtist aa WHERE aa.artist.id = :artistId")
    List<Album> findAlbumsByArtistId(@Param("artistId") Long artistId);

    List<AlbumArtist> findByArtistId(Long artistId);

    Optional<AlbumArtist> findByAlbumId(Long albumId);
}


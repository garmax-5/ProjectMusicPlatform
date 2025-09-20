package com.example.catalog_microservice.repository;

import com.example.catalog_microservice.model.AlbumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumTypeRepository extends JpaRepository<AlbumType, Long> {
}

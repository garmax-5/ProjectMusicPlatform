package com.example.catalog_microservice.repository;

import com.example.catalog_microservice.model.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaTypeRepository extends JpaRepository<MediaType, Long> {
    Optional<MediaType> findByName(String name);
}


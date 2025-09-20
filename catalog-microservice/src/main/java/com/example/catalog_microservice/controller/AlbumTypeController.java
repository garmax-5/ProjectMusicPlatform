package com.example.catalog_microservice.controller;

import com.example.catalog_microservice.model.AlbumType;
import com.example.catalog_microservice.repository.AlbumTypeRepository;
import com.example.catalog_microservice.dto.AlbumTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/catalog/album-types")
@RequiredArgsConstructor
public class AlbumTypeController {

    private final AlbumTypeRepository albumTypeRepository;

    @GetMapping
    public ResponseEntity<List<AlbumTypeDTO>> getAllAlbumTypes() {
        List<AlbumTypeDTO> list = albumTypeRepository.findAll().stream()
                .map(type -> {
                    AlbumTypeDTO dto = new AlbumTypeDTO();
                    dto.setId(type.getId());
                    dto.setName(type.getName());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}

package com.example.catalog_microservice.controller;

import com.example.catalog_microservice.model.MediaType;
import com.example.catalog_microservice.service.MediaTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/media-types")
public class MediaTypeController {
    private final MediaTypeService mediaTypeService;

    public MediaTypeController(MediaTypeService mediaTypeService) {
        this.mediaTypeService = mediaTypeService;
    }

    @GetMapping
    public ResponseEntity<List<MediaType>> getAllMediaTypes() {
        return ResponseEntity.ok(mediaTypeService.getAllMediaTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaType> getMediaTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(mediaTypeService.getMediaTypeById(id));
    }

    @PostMapping
    public ResponseEntity<MediaType> createMediaType(@RequestBody MediaType mediaType) {
        return ResponseEntity.ok(mediaTypeService.createMediaType(mediaType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaType> updateMediaType(@PathVariable Long id, @RequestBody MediaType mediaType) {
        return ResponseEntity.ok(mediaTypeService.updateMediaType(id, mediaType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMediaType(@PathVariable Long id) {
        mediaTypeService.deleteMediaType(id);
        return ResponseEntity.noContent().build();
    }
}


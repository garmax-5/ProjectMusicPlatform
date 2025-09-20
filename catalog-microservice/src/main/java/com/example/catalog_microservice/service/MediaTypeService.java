package com.example.catalog_microservice.service;


import com.example.catalog_microservice.model.MediaType;
import com.example.catalog_microservice.repository.MediaTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MediaTypeService {
    private final MediaTypeRepository mediaTypeRepository;

    public MediaTypeService(MediaTypeRepository mediaTypeRepository) {
        this.mediaTypeRepository = mediaTypeRepository;
    }

    public List<MediaType> getAllMediaTypes() {
        return mediaTypeRepository.findAll();
    }

    public MediaType getMediaTypeById(Long id) {
        return mediaTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Тип медиа не найден"));
    }

    public MediaType createMediaType(MediaType mediaType) {
        if (mediaTypeRepository.findByName(mediaType.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Тип медиа с таким названием уже существует");
        }
        return mediaTypeRepository.save(mediaType);
    }

    public MediaType updateMediaType(Long id, MediaType mediaType) {
        MediaType existingMediaType = getMediaTypeById(id);
        existingMediaType.setName(mediaType.getName());
        return mediaTypeRepository.save(existingMediaType);
    }

    public void deleteMediaType(Long id) {
        if (!mediaTypeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Тип медиа с указанным ID не существует");
        }
        mediaTypeRepository.deleteById(id);
    }
}


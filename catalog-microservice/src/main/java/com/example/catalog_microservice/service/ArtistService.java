package com.example.catalog_microservice.service;

import com.example.catalog_microservice.dto.ArtistRequestDTO;
import com.example.catalog_microservice.dto.ArtistResponseDTO;
import com.example.catalog_microservice.model.Artist;
import com.example.catalog_microservice.repository.ArtistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<ArtistResponseDTO> getAllArtistDTOs() {
        return artistRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ArtistResponseDTO getArtistByIdDTO(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Исполнитель не найден"));
        return convertToDTO(artist);
    }

    public ArtistResponseDTO createArtistFromDTO(ArtistRequestDTO dto) {
        Artist artist = new Artist();
        artist.setName(dto.getName());
        artist.setListenerCount(dto.getListenerCount());
        validateArtist(artist);
        return convertToDTO(artistRepository.save(artist));
    }

    public ArtistResponseDTO updateArtistFromDTO(Long id, ArtistRequestDTO dto) {
        Artist existingArtist = artistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Исполнитель не найден"));
        existingArtist.setName(dto.getName());
        existingArtist.setListenerCount(dto.getListenerCount());
        validateArtist(existingArtist);
        return convertToDTO(artistRepository.save(existingArtist));
    }

    public void deleteArtist(Long id) {
        if (!artistRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Исполнитель с указанным ID не существует");
        }
        artistRepository.deleteById(id);
    }

    public Optional<Long> findArtistIdByName(String name) {
        return artistRepository.findAll().stream()
                .filter(a -> a.getName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .map(Artist::getId);
    }

    private void validateArtist(Artist artist) {
        if (artist.getName() == null || artist.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Имя исполнителя не может быть пустым");
        }
    }

    private ArtistResponseDTO convertToDTO(Artist artist) {
        return new ArtistResponseDTO(artist.getId(), artist.getName(), artist.getListenerCount());
    }

    public List<ArtistResponseDTO> searchArtistsByName(String namePart) {
        return artistRepository.findAll().stream()
                .filter(a -> a.getName().toLowerCase().contains(namePart.trim().toLowerCase()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

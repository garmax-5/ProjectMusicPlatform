package com.example.catalog_microservice.service;

import com.example.catalog_microservice.model.Genre;
import com.example.catalog_microservice.repository.GenreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Жанр не найден"));
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre updateGenre(Long id, Genre genre) {
        Genre existingGenre = getGenreById(id);
        existingGenre.setName(genre.getName());
        return genreRepository.save(existingGenre);
    }

    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Жанр не найден");
        }
        genreRepository.deleteById(id);
    }
}

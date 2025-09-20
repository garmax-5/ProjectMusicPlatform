package com.example.catalog_microservice.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/catalog/images")
public class CatalogImageController {

    private final Path rootLocation = Paths.get("C:/Users/Maxim/Images/");

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            // Убедимся, что файл не выходит за пределы разрешенной директории
            Path file = rootLocation.resolve(filename).normalize();

            if (!file.startsWith(rootLocation)) {
                // Предотвращаем доступ к файлам вне заданной папки
                return ResponseEntity.badRequest().build();
            }

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                // Отправляем файл с соответствующими заголовками
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

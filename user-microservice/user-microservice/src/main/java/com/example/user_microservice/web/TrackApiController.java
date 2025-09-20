package com.example.user_microservice.web;

import com.example.user_microservice.client.CatalogClient;
import com.example.user_microservice.dto.TrackWithCoverDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/tracks")
public class TrackApiController {

    private final CatalogClient catalogClient;

    public TrackApiController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping("/with-covers")
    public List<TrackWithCoverDTO> getTracksWithCovers() {
        return catalogClient.getAllTracksWithCovers();
    }
}

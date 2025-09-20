package com.example.user_microservice.web;

import com.example.user_microservice.client.CatalogClient;
import com.example.user_microservice.dto.AlbumDisplayDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumWebController {

    private final CatalogClient catalogClient;

    @GetMapping("/{albumId}")
    public String showAlbum(@PathVariable Long albumId, Model model) {
        AlbumDisplayDTO album = catalogClient.getAlbumById(albumId);
        model.addAttribute("album", album);
        return "album-view";
    }
}
package com.example.user_microservice.web;

import com.example.user_microservice.client.CatalogClient;
import com.example.user_microservice.dto.AlbumCreateDTO;
import com.example.user_microservice.dto.AlbumResponseDTO;
import com.example.user_microservice.dto.AlbumTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/albums")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
public class AdminAlbumWebController {

    private final CatalogClient catalogClient;

    @GetMapping
    public String showAlbumsPage(Model model) {
        List<AlbumTypeDTO> albumTypes = catalogClient.getAllAlbumTypes();
        model.addAttribute("albumTypes", albumTypes);
        model.addAttribute("newAlbum", new AlbumCreateDTO());
        return "admin/albums";
    }

    @PostMapping("/create")
    public String createAlbum(@ModelAttribute("newAlbum") AlbumCreateDTO dto) {
        // Создание альбома
        AlbumResponseDTO createdAlbum = catalogClient.createAlbum(dto);

        // Поиск исполнителя по имени
        Long artistId = catalogClient.findArtistIdByName(dto.getArtistName());

        // Создание связи album_artist
        catalogClient.addAlbumArtist(createdAlbum.getId(), artistId);

        return "redirect:/admin/albums";
    }

    @PostMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        catalogClient.deleteAlbum(id);
        return "redirect:/admin/albums";
    }

    @GetMapping("/search")
    public String searchAlbumByName(@RequestParam("query") String query, Model model) {
        List<AlbumResponseDTO> foundAlbums = catalogClient.searchAlbumsByName(query);
        List<AlbumTypeDTO> albumTypes = catalogClient.getAllAlbumTypes();

        model.addAttribute("albums", foundAlbums); // для таблицы
        model.addAttribute("albumTypes", albumTypes); // для формы создания
        model.addAttribute("newAlbum", new AlbumCreateDTO()); // для формы создания

        return "admin/albums";
    }

}

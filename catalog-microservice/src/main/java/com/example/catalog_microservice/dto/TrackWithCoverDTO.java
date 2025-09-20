package com.example.catalog_microservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackWithCoverDTO {
    private Long id;
    private String name;
    private Integer duration;
    private String artistName;
    private String fileUrl;
    private Long albumId;
    private Long genreId;
    private Long mediaTypeId;
    private String coverImage;
}

package com.maemae.escaperoom.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ThemeListDTO {

    private Long themeId;
    private String themeName;
    private String genre;
    private Integer recommendStart;
    private Integer recommendEnd;
    private String imageUrl;

    private String cafeName;
    private String location;

    private Double ratingAvg;

    @QueryProjection
    public ThemeListDTO(Long themeId, String themeName, String genre,
                        Integer recommendStart, Integer recommendEnd, String imageUrl,
                        String cafeName, String location, Double ratingAvg) {
        this.themeId = themeId;
        this.themeName = themeName;
        this.genre = genre;
        this.recommendStart = recommendStart;
        this.recommendEnd = recommendEnd;
        this.imageUrl = imageUrl;
        this.cafeName = cafeName;
        this.location = location;
        this.ratingAvg = ratingAvg;
    }
}

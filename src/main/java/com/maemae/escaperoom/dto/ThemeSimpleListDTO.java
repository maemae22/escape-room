package com.maemae.escaperoom.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ThemeSimpleListDTO {

    private Long themeId;
    private String themeName;
    private String genre;
    private Integer recommendStart;
    private Integer recommendEnd;
    private String imageUrl;

    private Double ratingAvg;

    @QueryProjection
    public ThemeSimpleListDTO(Long themeId, String themeName, String genre,
                              Integer recommendStart, Integer recommendEnd, String imageUrl, Double ratingAvg) {
        this.themeId = themeId;
        this.themeName = themeName;
        this.genre = genre;
        this.recommendStart = recommendStart;
        this.recommendEnd = recommendEnd;
        this.imageUrl = imageUrl;
        this.ratingAvg = ratingAvg;
    }

    public void changeImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

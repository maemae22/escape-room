package com.maemae.escaperoom.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ThemeDetailDTO {

    private Long themeId;
    private String name;
    private String genre;
    private String activity;
    private Integer difficult;
    private Integer limitTime;
    private Integer recommendStart;
    private Integer recommendEnd;
    private String info;
    private String imageUrl;

    private Long cafeId;
    private String cafeName;
    private String domain;
    private String location;

    private Double ratingAvg;

    @QueryProjection
    public ThemeDetailDTO(Long themeId, String name, String genre, String activity, Integer difficult,
                          Integer limitTime, Integer recommendStart, Integer recommendEnd, String info, String imageUrl,
                          Long cafeId, String cafeName, String domain, String location, Double ratingAvg) {
        this.themeId = themeId;
        this.name = name;
        this.genre = genre;
        this.activity = activity;
        this.difficult = difficult;
        this.limitTime = limitTime;
        this.recommendStart = recommendStart;
        this.recommendEnd = recommendEnd;
        this.info = info;
        this.imageUrl = imageUrl;
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.domain = domain;
        this.location = location;
        this.ratingAvg = ratingAvg;
    }

    public void changeImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

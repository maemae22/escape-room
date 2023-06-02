package com.maemae.escaperoom.dto;

import com.maemae.escaperoom.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewOneDTO {

    private Long reviewId;
    private Long themeId;
    private String nickname;
    private String playdate;
    private Double difficult;
    private String clear;
    private String time;
    private Integer hint;
    private String content;
    private LocalDateTime indate;
    private Double rating;

    public ReviewOneDTO(Review review) {
        this.reviewId = review.getId();
        this.themeId = review.getTheme().getId();
        this.nickname = review.getNickname();
        this.playdate = review.getPlaydate().toString().substring(0, 10);
        this.difficult = review.getDifficult();
        this.clear = review.getClear();
        this.time = review.getTime();
        this.hint = review.getHint();
        this.indate = review.getIndate();
        this.content = review.getContent();
        this.rating = review.getRating();
    }
}

package com.maemae.escaperoom.dto;

import com.maemae.escaperoom.entity.Review;
import com.maemae.escaperoom.entity.Theme;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ReviewDTO {

    private Long themeId;
    private String nickname;
    private String password;
    private String playdate;
    private Double difficult;
    private String clear;
    private String time;
    private Integer hint;
    private String content;
    private Double rating;

    public Review toEntity() {
        LocalDate date = LocalDate.parse(playdate, DateTimeFormatter.ISO_DATE);

        return Review.builder()
                .theme(Theme.builder().id(themeId).build())
                .nickname(nickname)
                .password(password)
                .playdate(date.atStartOfDay())
                .difficult(difficult)
                .clear(clear)
                .time(time)
                .hint(hint)
                .content(content)
                .rating(rating)
                .build();
    }
}

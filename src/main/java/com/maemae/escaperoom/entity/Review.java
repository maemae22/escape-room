package com.maemae.escaperoom.entity;

import com.maemae.escaperoom.dto.ReviewUpdateDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;

    private String nickname;
    private String password;
    private LocalDateTime playdate;
    private Double difficult;
    private String clear;
    private String time;
    private Integer hint;

    @Column(length = 700)
    private String content;

    @CreatedDate
    private LocalDateTime indate;

    private Double rating;

    public void updateReview(ReviewUpdateDTO reviewUpdateDTO) {
        password = reviewUpdateDTO.getPassword();
        playdate = LocalDate.parse(reviewUpdateDTO.getPlaydate(), DateTimeFormatter.ISO_DATE).atStartOfDay();
        difficult = reviewUpdateDTO.getDifficult();
        clear = reviewUpdateDTO.getClear();
        time = reviewUpdateDTO.getTime();
        hint = reviewUpdateDTO.getHint();
        content = reviewUpdateDTO.getContent();
        rating = reviewUpdateDTO.getRating();
    }
}

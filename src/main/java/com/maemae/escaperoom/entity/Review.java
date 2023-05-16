package com.maemae.escaperoom.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private LocalDateTime indate;
    private Double rating;
}

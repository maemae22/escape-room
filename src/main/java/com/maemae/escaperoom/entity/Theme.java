package com.maemae.escaperoom.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    private String name;
    private String genre;
    private String activity;
    private int difficult;
    private int limitTime;
    private int recommendStart;
    private int recommendEnd;

    @Column(length = 500)
    private String info;

    private String imageUrl;

    @OneToMany(mappedBy = "theme")
    private List<Review> reviews = new ArrayList<>();


    //==연관관계 편의 메서드==//
    public void changeCafe(Cafe cafe) {
        this.cafe = cafe;
        cafe.getThemes().add(this);
    }
}

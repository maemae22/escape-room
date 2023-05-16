package com.maemae.escaperoom.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String phoneNumber;
    private String bhours;
    private String address;
    private String domain;
    private String location;

    @OneToMany(mappedBy = "cafe")
    private List<Theme> themes = new ArrayList<>();

}

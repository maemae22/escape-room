package com.maemae.escaperoom.dto;

import com.maemae.escaperoom.entity.Cafe;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CafeDTO {

    private Long cafeId;
    private String name;
    private String phoneNumber;
    private String bhours;
    private String address;
    private String domain;
    private String location;

    public CafeDTO(Cafe cafe) {
        this.cafeId = cafe.getId();
        this.name = cafe.getName();
        this.phoneNumber = cafe.getPhoneNumber();
        this.bhours = cafe.getBhours();
        this.address = cafe.getAddress();
        this.domain = cafe.getDomain();
        this.location = cafe.getLocation();
    }

    @QueryProjection
    public CafeDTO(Long cafeId, String name, String phoneNumber, String bhours, String address, String domain, String location) {
        this.cafeId = cafeId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bhours = bhours;
        this.address = address;
        this.domain = domain;
        this.location = location;
    }
}

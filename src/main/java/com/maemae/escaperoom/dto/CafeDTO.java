package com.maemae.escaperoom.dto;

import com.maemae.escaperoom.entity.Cafe;
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
}

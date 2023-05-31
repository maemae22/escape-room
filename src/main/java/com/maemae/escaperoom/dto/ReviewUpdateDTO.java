package com.maemae.escaperoom.dto;

import lombok.Data;

@Data
public class ReviewUpdateDTO {

    private String password;
    private String playdate;
    private Double difficult;
    private String clear;
    private String time;
    private Integer hint;
    private String content;
    private Double rating;
}

package com.maemae.escaperoom.dto;

import lombok.Data;

import java.util.List;

@Data
public class ThemeSearchCondition {

    private List<String> location; // 선택한 지역 list
    private List<String> genre; // 선택한 장르 list
    private List<Integer> difficult; // 선택한 난이도 (1~5 복수 선택 가능)
    private List<String> activity; // 선택한 활동성 list (`낮음, 보통, 높음` 복수 선택 가능)
    private Integer peopleNum; // 플레이할 인원
    private String keyword; // keyword가 포함되는 테마 이름 검색
    private String sorting; // 리뷰 높은 순, 이름순, 지역순 정렬 기능
}

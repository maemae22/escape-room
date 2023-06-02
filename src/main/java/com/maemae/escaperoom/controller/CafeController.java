package com.maemae.escaperoom.controller;

import com.maemae.escaperoom.dto.CafeDTO;
import com.maemae.escaperoom.service.CafeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/cafe/all")
    public Result cafeAllList(@PageableDefault(size = 12, sort = "location") Pageable pageable) {
        Page<CafeDTO> cafeAllList = cafeService.cafeAllList(pageable);
        return new Result(cafeAllList);
    }

    @GetMapping("/cafe/search")
    public Result cafeSearchList(@RequestParam(required = false) List<String> loc,
                                 @RequestParam(required = false) String keyword,
                                 Pageable pageable) {
        Page<CafeDTO> cafeSearchList = cafeService.cafeSearchList(loc, keyword, pageable);
        return new Result(cafeSearchList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}

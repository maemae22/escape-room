package com.maemae.escaperoom.controller;

import com.maemae.escaperoom.dto.CafeDTO;
import com.maemae.escaperoom.service.CafeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/cafe/all")
    public Page<CafeDTO> cafeAllList(@PageableDefault(size = 12, sort = "name") Pageable pageable) {
        return cafeService.cafeAllList(pageable);
    }
}

package com.maemae.escaperoom.controller;

import com.maemae.escaperoom.dto.ThemeDetailDTO;
import com.maemae.escaperoom.dto.ThemeListDTO;
import com.maemae.escaperoom.service.ThemeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping("/theme/all")
    public Result themeAllList(Pageable pageable) {
        Page<ThemeListDTO> themeList = themeService.themeAllList(pageable);
        return new Result(themeList);
    }

    @GetMapping("/theme-detail/{themeId}")
    public Result themeDetail(@PathVariable Long themeId) {
        ThemeDetailDTO themeDetailDTO = themeService.themeDetail(themeId);
        return new Result(themeDetailDTO);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}

package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.dto.ThemeDetailDTO;
import com.maemae.escaperoom.dto.ThemeListDTO;
import com.maemae.escaperoom.dto.ThemeSearchCondition;
import com.maemae.escaperoom.dto.ThemeSimpleListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThemeRepositoryCustom {
    Page<ThemeListDTO> themeAllListPage(Pageable pageable);
    ThemeDetailDTO themeDetail(Long themeId);
    List<ThemeSimpleListDTO> themeListByCafeId(Long cafeId);
    List<ThemeSimpleListDTO> sameCafeOtherThemeList(Long themeId);
    Page<ThemeListDTO> themeSearchPage(ThemeSearchCondition condition, Pageable pageable);
}

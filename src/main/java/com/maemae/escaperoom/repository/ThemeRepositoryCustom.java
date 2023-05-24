package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.dto.ThemeDetailDTO;
import com.maemae.escaperoom.dto.ThemeListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThemeRepositoryCustom {
    Page<ThemeListDTO> themeAllListPage(Pageable pageable);
    ThemeDetailDTO themeDetail(Long themeId);
}

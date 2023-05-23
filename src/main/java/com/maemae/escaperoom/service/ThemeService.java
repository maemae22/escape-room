package com.maemae.escaperoom.service;

import com.maemae.escaperoom.dto.ThemeListDTO;
import com.maemae.escaperoom.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;

    public Page<ThemeListDTO> themeAllList(Pageable pageable) {
        return themeRepository.themeAllListPage(pageable);
    }
}

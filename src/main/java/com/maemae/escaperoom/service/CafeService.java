package com.maemae.escaperoom.service;

import com.maemae.escaperoom.dto.CafeDTO;
import com.maemae.escaperoom.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;

    public Page<CafeDTO> cafeAllList(Pageable pageable) {
        return cafeRepository.findAll(pageable).map(CafeDTO::new);
    }

    public Page<CafeDTO> cafeSearchList(List<String> loc, String keyword, Pageable pageable) {
        return cafeRepository.cafeSearchPage(loc, keyword, pageable);
    }
}

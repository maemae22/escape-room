package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.dto.CafeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    Page<CafeDTO> cafeSearchPage(List<String> loc, String keyword, Pageable pageable);
}

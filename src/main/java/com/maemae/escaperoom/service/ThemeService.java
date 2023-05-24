package com.maemae.escaperoom.service;

import com.maemae.escaperoom.dto.ThemeDetailDTO;
import com.maemae.escaperoom.dto.ThemeListDTO;
import com.maemae.escaperoom.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final S3UploadService s3UploadService;

    public Page<ThemeListDTO> themeAllList(Pageable pageable) {

        Page<ThemeListDTO> page = themeRepository.themeAllListPage(pageable);

        List<ThemeListDTO> content = page.getContent();

        for (ThemeListDTO dto : content) {
            String S3imgUrl = s3UploadService.getThumbnailPath("theme_img/"+dto.getImageUrl());
            dto.changeImageUrl(S3imgUrl);
        }

        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    public ThemeDetailDTO themeDetail(Long themeId) {

        ThemeDetailDTO themeDetailDTO = themeRepository.themeDetail(themeId);

        String S3imgUrl = s3UploadService.getThumbnailPath("theme_img/"+themeDetailDTO.getImageUrl());
        themeDetailDTO.changeImageUrl(S3imgUrl);

        return themeDetailDTO;
    }
}

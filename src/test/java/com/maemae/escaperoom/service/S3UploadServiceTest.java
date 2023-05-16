package com.maemae.escaperoom.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class S3UploadServiceTest {

    @Autowired
    private S3UploadService s3UploadService;

    @DisplayName("저장된 이미지 url S3에서 찾기")
    @Test
    public void findImgUrlByS3Bucket() {
        String imgUrl = s3UploadService.getThumbnailPath("theme_img/게슈타포_큐브이스케이프.PNG");
        log.info(imgUrl);
        Assertions.assertThat(imgUrl).isNotNull();
    }
}

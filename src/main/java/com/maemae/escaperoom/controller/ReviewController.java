package com.maemae.escaperoom.controller;

import com.maemae.escaperoom.dto.ReviewDTO;
import com.maemae.escaperoom.dto.ReviewOneDTO;
import com.maemae.escaperoom.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review/insert")
    public HashMap<String, String> reviewInsert(ReviewDTO reviewDTO) {
        return reviewService.reviewInsert(reviewDTO);
    }

    @GetMapping("/review")
    public Result reviewOne(@RequestParam(value = "id") Long reviewId) {
        ReviewOneDTO reviewOneDTO = reviewService.reviewOneByReviewId(reviewId);
        return new Result(reviewOneDTO);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}

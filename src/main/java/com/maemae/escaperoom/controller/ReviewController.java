package com.maemae.escaperoom.controller;

import com.maemae.escaperoom.dto.ReviewDTO;
import com.maemae.escaperoom.dto.ReviewOneDTO;
import com.maemae.escaperoom.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> reviewDelete(@PathVariable Long reviewId) {
        String deleteReviewResult = reviewService.reviewDelete(reviewId);
        if (deleteReviewResult.equals("success")) {
            return new ResponseEntity(deleteReviewResult, HttpStatus.OK);
        } else {
            return new ResponseEntity(deleteReviewResult, HttpStatus.BAD_REQUEST);
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}

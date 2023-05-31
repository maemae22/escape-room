package com.maemae.escaperoom.service;

import com.maemae.escaperoom.dto.ReviewDTO;
import com.maemae.escaperoom.dto.ReviewOneDTO;
import com.maemae.escaperoom.entity.Review;
import com.maemae.escaperoom.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public HashMap<String, String> reviewInsert(ReviewDTO reviewDTO) {
        HashMap<String, String> result = new HashMap<>();

        try {
            reviewRepository.save(reviewDTO.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "failed");
            return result;
        }
        result.put("resultCode", "success");
        return result;
    }

    public ReviewOneDTO reviewOneByReviewId(Long reviewId) {
        try {
            Review review = reviewRepository.findReviewOneById(reviewId).get();
            return new ReviewOneDTO(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String reviewDelete(Long reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    public Page<ReviewOneDTO> reviewListByThemeId(Long themeId, Pageable pageable) {
        try {
            Page<Review> reviewListByThemeId = reviewRepository.findReviewListByThemeId(themeId, pageable);
            return reviewListByThemeId.map(ReviewOneDTO::new);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

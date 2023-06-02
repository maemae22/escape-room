package com.maemae.escaperoom.service;

import com.maemae.escaperoom.dto.ReviewDTO;
import com.maemae.escaperoom.dto.ReviewOneDTO;
import com.maemae.escaperoom.dto.ReviewPasswordCheckDTO;
import com.maemae.escaperoom.dto.ReviewUpdateDTO;
import com.maemae.escaperoom.entity.Review;
import com.maemae.escaperoom.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public String reviewInsert(ReviewDTO reviewDTO) {
        try {
            reviewRepository.save(reviewDTO.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
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

    @Transactional
    public String reviewUpdate(Long reviewId, ReviewUpdateDTO reviewUpdateDTO) {
        try {
            Review findReview = reviewRepository.findReviewOneById(reviewId).get();
            findReview.updateReview(reviewUpdateDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    public String reviewPasswordCheck(ReviewPasswordCheckDTO reviewPasswordCheckDTO) {
        try {
            String findPassword = reviewRepository.findReviewPasswordById(reviewPasswordCheckDTO.getReviewId());

            if (reviewPasswordCheckDTO.getPassword().equals(findPassword)) {
                return "success";
            } else {
                return "failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }
}

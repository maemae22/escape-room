package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findReviewOneById(Long reviewId);
}

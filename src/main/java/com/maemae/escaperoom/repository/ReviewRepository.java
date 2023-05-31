package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findReviewOneById(Long reviewId);

    @Query("select r from Review r where r.theme.id= :themeId")
    Page<Review> findReviewListByThemeId(@Param("themeId") Long themeId, Pageable pageable);

    @Query("select r.password from Review r where r.id= :reviewId")
    String findReviewPasswordById(@Param("reviewId") Long reviewId);
}

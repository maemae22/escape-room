package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

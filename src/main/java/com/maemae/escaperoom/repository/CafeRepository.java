package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.entity.Cafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Long>, CafeRepositoryCustom {

    Page<Cafe> findAll(Pageable pageable);
}

package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long>, ThemeRepositoryCustom {

}

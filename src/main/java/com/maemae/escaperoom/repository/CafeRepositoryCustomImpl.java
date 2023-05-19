package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.dto.CafeDTO;
import com.maemae.escaperoom.dto.QCafeDTO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.maemae.escaperoom.entity.QCafe.cafe;
import static org.springframework.util.StringUtils.hasText;

public class CafeRepositoryCustomImpl implements CafeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CafeRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<CafeDTO> cafeSearchPage(List<String> loc, String keyword, Pageable pageable) {

        QueryResults<CafeDTO> searchResults = queryFactory
                .select(new QCafeDTO(cafe.id, cafe.name, cafe.phoneNumber,
                        cafe.bhours, cafe.address, cafe.domain, cafe.location))
                .from(cafe)
                .where(locIn(loc), keywordContaining(keyword))
                .orderBy(cafe.location.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<CafeDTO> contents = searchResults.getResults();
        long total = searchResults.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    private BooleanExpression locIn(List<String> loc) {
        return loc == null ? null : ( !loc.isEmpty() ? cafe.location.in(loc) : null );
    }

    private BooleanExpression keywordContaining(String keyword) {
        return hasText(keyword) ? cafe.name.contains(keyword) : null;
    }
}

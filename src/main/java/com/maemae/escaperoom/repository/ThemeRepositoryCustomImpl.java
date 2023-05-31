package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.dto.*;
import com.maemae.escaperoom.entity.QCafe;
import com.maemae.escaperoom.entity.QReview;
import com.maemae.escaperoom.entity.QTheme;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.maemae.escaperoom.entity.QCafe.*;
import static com.maemae.escaperoom.entity.QReview.*;
import static com.maemae.escaperoom.entity.QTheme.*;

public class ThemeRepositoryCustomImpl implements ThemeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ThemeRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<ThemeListDTO> themeAllListPage(Pageable pageable) {

        List<ThemeListDTO> themeListContent = queryFactory
                .select(new QThemeListDTO(
                        theme.id,
                        theme.name,
                        theme.genre,
                        theme.recommendStart,
                        theme.recommendEnd,
                        theme.imageUrl,
                        cafe.name,
                        cafe.location,
                        Expressions.template(Double.class, "ROUND({0}, 2)", review.rating.avg().coalesce(-1.0))
                ))
                .from(theme)
                .leftJoin(theme.cafe, cafe)
                .leftJoin(theme.reviews, review)
                .groupBy(theme.id)
                .orderBy(review.rating.avg().desc().nullsLast())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(theme.count())
                .from(theme);

        return PageableExecutionUtils.getPage(themeListContent, pageable, countQuery::fetchOne);
    }

    @Override
    public ThemeDetailDTO themeDetail(Long themeId) {

        return queryFactory
                .select(new QThemeDetailDTO(
                        theme.id,
                        theme.name,
                        theme.genre,
                        theme.activity,
                        theme.difficult,
                        theme.limitTime,
                        theme.recommendStart,
                        theme.recommendEnd,
                        theme.info,
                        theme.imageUrl,
                        cafe.id,
                        cafe.name,
                        cafe.domain,
                        cafe.location,
                        Expressions.template(Double.class, "ROUND({0}, 2)", review.rating.avg().coalesce(-1.0))
                ))
                .from(theme)
                .leftJoin(theme.cafe, cafe)
                .leftJoin(theme.reviews, review)
                .where(theme.id.eq(themeId))
                .fetchOne();
    }

    @Override
    public List<ThemeSimpleListDTO> themeListByCafeId(Long cafeId) {

        return queryFactory
                .select(new QThemeSimpleListDTO(
                        theme.id,
                        theme.name,
                        theme.genre,
                        theme.recommendStart,
                        theme.recommendEnd,
                        theme.imageUrl,
                        Expressions.template(Double.class, "ROUND({0}, 2)", review.rating.avg().coalesce(-1.0))
                ))
                .from(theme)
                .leftJoin(theme.reviews, review)
                .where(theme.cafe.id.eq(cafeId))
                .groupBy(theme.id)
                .orderBy(review.rating.avg().desc().nullsLast())
                .fetch();
    }
}

package com.maemae.escaperoom.repository;

import com.maemae.escaperoom.dto.*;
import com.maemae.escaperoom.entity.QCafe;
import com.maemae.escaperoom.entity.QReview;
import com.maemae.escaperoom.entity.QTheme;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.maemae.escaperoom.entity.QCafe.*;
import static com.maemae.escaperoom.entity.QReview.*;
import static com.maemae.escaperoom.entity.QTheme.*;
import static org.springframework.util.StringUtils.hasText;

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

    @Override
    public List<ThemeSimpleListDTO> sameCafeOtherThemeList(Long themeId) {

        QTheme themeSub = new QTheme("themeSub");

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
                .where(theme.cafe.id.eq(
                        JPAExpressions
                                .select(themeSub.cafe.id)
                                .from(themeSub)
                                .where(themeSub.id.eq(themeId))
                ), theme.id.ne(themeId))
                .groupBy(theme.id)
                .fetch();
    }

    @Override
    public Page<ThemeListDTO> themeSearchPage(ThemeSearchCondition condition, Pageable pageable) {

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
                .where(
                        locationIn(condition.getLocation()),
                        genreIn(condition.getGenre()),
                        difficultIn(condition.getDifficult()),
                        activityIn(condition.getActivity()),
                        peopleNumInclude(condition.getPeopleNum()),
                        keywordContaining(condition.getKeyword())
                )
                .groupBy(theme.id)
                .orderBy(sort(condition.getSorting()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(theme.count())
                .from(theme)
                .leftJoin(theme.cafe, cafe)
                .where(
                        locationIn(condition.getLocation()),
                        genreIn(condition.getGenre()),
                        difficultIn(condition.getDifficult()),
                        activityIn(condition.getActivity()),
                        peopleNumInclude(condition.getPeopleNum()),
                        keywordContaining(condition.getKeyword())
                );

        return PageableExecutionUtils.getPage(themeListContent, pageable, countQuery::fetchOne);
    }

    private BooleanExpression locationIn(List<String> locations) {
        return locations == null ? null : ( !locations.isEmpty() ? cafe.location.in(locations) : null );
    }

    private BooleanExpression genreIn(List<String> genres) {
        return genres == null ? null : ( !genres.isEmpty() ? theme.genre.in(genres) : null );
    }

    private BooleanExpression difficultIn(List<Integer> difficult) {
        return difficult == null ? null : ( !difficult.isEmpty() ? theme.difficult.in(difficult) : null );
    }

    private BooleanExpression activityIn(List<String> activities) {
        return activities == null ? null : ( !activities.isEmpty() ? theme.activity.in(activities) : null );
    }

    private BooleanExpression peopleNumInclude(Integer peopleNum) {
        return peopleNum != null ? theme.recommendStart.loe(peopleNum).and(theme.recommendEnd.goe(peopleNum)) : null;
    }

    private BooleanExpression keywordContaining(String keyword) {
        return hasText(keyword) ? theme.name.contains(keyword) : null;
    }

    private OrderSpecifier sort(String sorting) {
        if (hasText(sorting) && sorting.equals("name")) {
            return theme.name.asc();
        } else if (hasText(sorting) && sorting.equals("location")) {
            return cafe.location.asc();
        } else {
            return review.rating.avg().desc().nullsLast();
        }
    }
}

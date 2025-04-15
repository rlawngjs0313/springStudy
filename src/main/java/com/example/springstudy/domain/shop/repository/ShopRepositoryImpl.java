package com.example.springstudy.domain.shop.repository;

import com.example.springstudy.domain.shop.entity.QShop;
import com.example.springstudy.domain.shop.entity.Shop;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopRepositoryImpl implements ShopRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QShop shop = QShop.shop;
}

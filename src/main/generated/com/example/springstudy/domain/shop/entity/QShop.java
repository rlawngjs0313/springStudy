package com.example.springstudy.domain.shop.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShop is a Querydsl query type for Shop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShop extends EntityPathBase<Shop> {

    private static final long serialVersionUID = -1553041488L;

    public static final QShop shop = new QShop("shop");

    public final NumberPath<Long> managerId = createNumber("managerId", Long.class);

    public final StringPath shopCity = createString("shopCity");

    public final NumberPath<Long> shopId = createNumber("shopId", Long.class);

    public final StringPath shopName = createString("shopName");

    public QShop(String variable) {
        super(Shop.class, forVariable(variable));
    }

    public QShop(Path<? extends Shop> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShop(PathMetadata metadata) {
        super(Shop.class, metadata);
    }

}


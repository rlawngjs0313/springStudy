package com.example.springstudy.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1369372492L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.example.springstudy.global.entity.QBaseEntity _super = new com.example.springstudy.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath reviewContent = createString("reviewContent");

    public final QReviewReply reviewReply;

    public final com.example.springstudy.domain.shop.entity.QShop shop;

    public final NumberPath<Integer> star = createNumber("star", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.example.springstudy.mapping.UserReview, com.example.springstudy.mapping.QUserReview> userReviewList = this.<com.example.springstudy.mapping.UserReview, com.example.springstudy.mapping.QUserReview>createList("userReviewList", com.example.springstudy.mapping.UserReview.class, com.example.springstudy.mapping.QUserReview.class, PathInits.DIRECT2);

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reviewReply = inits.isInitialized("reviewReply") ? new QReviewReply(forProperty("reviewReply"), inits.get("reviewReply")) : null;
        this.shop = inits.isInitialized("shop") ? new com.example.springstudy.domain.shop.entity.QShop(forProperty("shop")) : null;
    }

}


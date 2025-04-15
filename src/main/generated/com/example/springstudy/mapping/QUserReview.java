package com.example.springstudy.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserReview is a Querydsl query type for UserReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserReview extends EntityPathBase<UserReview> {

    private static final long serialVersionUID = -1544779354L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserReview userReview = new QUserReview("userReview");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.springstudy.domain.review.entity.QReview review;

    public final com.example.springstudy.domain.user.entity.QUser user;

    public QUserReview(String variable) {
        this(UserReview.class, forVariable(variable), INITS);
    }

    public QUserReview(Path<? extends UserReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserReview(PathMetadata metadata, PathInits inits) {
        this(UserReview.class, metadata, inits);
    }

    public QUserReview(Class<? extends UserReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new com.example.springstudy.domain.review.entity.QReview(forProperty("review"), inits.get("review")) : null;
        this.user = inits.isInitialized("user") ? new com.example.springstudy.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


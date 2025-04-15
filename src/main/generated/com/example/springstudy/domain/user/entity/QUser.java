package com.example.springstudy.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1297940954L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.example.springstudy.global.entity.QBaseEntity _super = new com.example.springstudy.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath pwd = createString("pwd");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<UserAgree, QUserAgree> userAgreeList = this.<UserAgree, QUserAgree>createList("userAgreeList", UserAgree.class, QUserAgree.class, PathInits.DIRECT2);

    public final ListPath<com.example.springstudy.mapping.UserAsk, com.example.springstudy.mapping.QUserAsk> userAskList = this.<com.example.springstudy.mapping.UserAsk, com.example.springstudy.mapping.QUserAsk>createList("userAskList", com.example.springstudy.mapping.UserAsk.class, com.example.springstudy.mapping.QUserAsk.class, PathInits.DIRECT2);

    public final ListPath<UserFavorite, QUserFavorite> userFavoriteList = this.<UserFavorite, QUserFavorite>createList("userFavoriteList", UserFavorite.class, QUserFavorite.class, PathInits.DIRECT2);

    public final QUserInfo userInfo;

    public final ListPath<com.example.springstudy.mapping.UserMission, com.example.springstudy.mapping.QUserMission> userMissionList = this.<com.example.springstudy.mapping.UserMission, com.example.springstudy.mapping.QUserMission>createList("userMissionList", com.example.springstudy.mapping.UserMission.class, com.example.springstudy.mapping.QUserMission.class, PathInits.DIRECT2);

    public final ListPath<com.example.springstudy.mapping.UserReview, com.example.springstudy.mapping.QUserReview> userReviewList = this.<com.example.springstudy.mapping.UserReview, com.example.springstudy.mapping.QUserReview>createList("userReviewList", com.example.springstudy.mapping.UserReview.class, com.example.springstudy.mapping.QUserReview.class, PathInits.DIRECT2);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo"), inits.get("userInfo")) : null;
    }

}


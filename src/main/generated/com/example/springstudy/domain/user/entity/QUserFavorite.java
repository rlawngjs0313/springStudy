package com.example.springstudy.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFavorite is a Querydsl query type for UserFavorite
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFavorite extends EntityPathBase<UserFavorite> {

    private static final long serialVersionUID = 1770960278L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFavorite userFavorite = new QUserFavorite("userFavorite");

    public final EnumPath<com.example.springstudy.domain.user.enums.Food> food = createEnum("food", com.example.springstudy.domain.user.enums.Food.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QUserFavorite(String variable) {
        this(UserFavorite.class, forVariable(variable), INITS);
    }

    public QUserFavorite(Path<? extends UserFavorite> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFavorite(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFavorite(PathMetadata metadata, PathInits inits) {
        this(UserFavorite.class, metadata, inits);
    }

    public QUserFavorite(Class<? extends UserFavorite> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}


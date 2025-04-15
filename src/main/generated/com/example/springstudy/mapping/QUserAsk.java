package com.example.springstudy.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAsk is a Querydsl query type for UserAsk
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAsk extends EntityPathBase<UserAsk> {

    private static final long serialVersionUID = 854571755L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAsk userAsk = new QUserAsk("userAsk");

    public final com.example.springstudy.domain.ask.entity.QAsk ask;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.springstudy.domain.user.entity.QUser user;

    public QUserAsk(String variable) {
        this(UserAsk.class, forVariable(variable), INITS);
    }

    public QUserAsk(Path<? extends UserAsk> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAsk(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAsk(PathMetadata metadata, PathInits inits) {
        this(UserAsk.class, metadata, inits);
    }

    public QUserAsk(Class<? extends UserAsk> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ask = inits.isInitialized("ask") ? new com.example.springstudy.domain.ask.entity.QAsk(forProperty("ask"), inits.get("ask")) : null;
        this.user = inits.isInitialized("user") ? new com.example.springstudy.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


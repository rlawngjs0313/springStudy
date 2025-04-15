package com.example.springstudy.domain.ask.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAsk is a Querydsl query type for Ask
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAsk extends EntityPathBase<Ask> {

    private static final long serialVersionUID = 1326755466L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAsk ask = new QAsk("ask");

    public final com.example.springstudy.global.entity.QBaseEntity _super = new com.example.springstudy.global.entity.QBaseEntity(this);

    public final EnumPath<com.example.springstudy.domain.ask.enums.AnsFlag> ansFlag = createEnum("ansFlag", com.example.springstudy.domain.ask.enums.AnsFlag.class);

    public final QAskAns askAns;

    public final StringPath askContent = createString("askContent");

    public final StringPath askTitle = createString("askTitle");

    public final EnumPath<com.example.springstudy.domain.ask.enums.AskType> askType = createEnum("askType", com.example.springstudy.domain.ask.enums.AskType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.example.springstudy.mapping.UserAsk, com.example.springstudy.mapping.QUserAsk> userAskList = this.<com.example.springstudy.mapping.UserAsk, com.example.springstudy.mapping.QUserAsk>createList("userAskList", com.example.springstudy.mapping.UserAsk.class, com.example.springstudy.mapping.QUserAsk.class, PathInits.DIRECT2);

    public QAsk(String variable) {
        this(Ask.class, forVariable(variable), INITS);
    }

    public QAsk(Path<? extends Ask> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAsk(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAsk(PathMetadata metadata, PathInits inits) {
        this(Ask.class, metadata, inits);
    }

    public QAsk(Class<? extends Ask> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.askAns = inits.isInitialized("askAns") ? new QAskAns(forProperty("askAns"), inits.get("askAns")) : null;
    }

}


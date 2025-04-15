package com.example.springstudy.domain.ask.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAskAns is a Querydsl query type for AskAns
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAskAns extends EntityPathBase<AskAns> {

    private static final long serialVersionUID = -1211871492L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAskAns askAns = new QAskAns("askAns");

    public final com.example.springstudy.global.entity.QBaseEntity _super = new com.example.springstudy.global.entity.QBaseEntity(this);

    public final StringPath ansContent = createString("ansContent");

    public final QAsk ask;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAskAns(String variable) {
        this(AskAns.class, forVariable(variable), INITS);
    }

    public QAskAns(Path<? extends AskAns> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAskAns(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAskAns(PathMetadata metadata, PathInits inits) {
        this(AskAns.class, metadata, inits);
    }

    public QAskAns(Class<? extends AskAns> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ask = inits.isInitialized("ask") ? new QAsk(forProperty("ask"), inits.get("ask")) : null;
    }

}


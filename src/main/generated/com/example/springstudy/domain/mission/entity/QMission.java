package com.example.springstudy.domain.mission.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMission is a Querydsl query type for Mission
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMission extends EntityPathBase<Mission> {

    private static final long serialVersionUID = -1949750742L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMission mission = new QMission("mission");

    public final com.example.springstudy.global.entity.QBaseEntity _super = new com.example.springstudy.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> missionReq = createNumber("missionReq", Integer.class);

    public final NumberPath<Integer> missionScore = createNumber("missionScore", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> missionTime = createDateTime("missionTime", java.time.LocalDateTime.class);

    public final com.example.springstudy.domain.shop.entity.QShop shop;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.example.springstudy.mapping.UserMission, com.example.springstudy.mapping.QUserMission> userMissionList = this.<com.example.springstudy.mapping.UserMission, com.example.springstudy.mapping.QUserMission>createList("userMissionList", com.example.springstudy.mapping.UserMission.class, com.example.springstudy.mapping.QUserMission.class, PathInits.DIRECT2);

    public QMission(String variable) {
        this(Mission.class, forVariable(variable), INITS);
    }

    public QMission(Path<? extends Mission> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMission(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMission(PathMetadata metadata, PathInits inits) {
        this(Mission.class, metadata, inits);
    }

    public QMission(Class<? extends Mission> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shop = inits.isInitialized("shop") ? new com.example.springstudy.domain.shop.entity.QShop(forProperty("shop")) : null;
    }

}


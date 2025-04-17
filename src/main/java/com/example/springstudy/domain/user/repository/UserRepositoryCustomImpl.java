package com.example.springstudy.domain.user.repository;

import com.example.springstudy.domain.user.dto.UserResDTO;
import com.example.springstudy.domain.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QUser user = QUser.user;

    @Override
    public UserResDTO.MyPageDTO findMyPage(Long userId) {
        return queryFactory
                .select(
                        Projections.fields(
                                UserResDTO.MyPageDTO.class,
                                user.userInfo.nickname,
                                user.email,
                                user.userInfo.phoneNum,
                                user.userInfo.userPoint
                        )
                )
                .from(user)
                .fetchOne();
    }
}

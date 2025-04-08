package com.example.springstudy.domain.user.entity;

import com.example.springstudy.global.entity.BaseEntity;
import com.example.springstudy.mapping.UserAsk;
import com.example.springstudy.mapping.UserMission;
import com.example.springstudy.mapping.UserReview;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "pwd")
    private String pwd;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserFavorite> userFavoriteList;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserAgree> userAgreeList;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserReview> userReviewList;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserMission> userMissionList;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserAsk> userAskList;
}

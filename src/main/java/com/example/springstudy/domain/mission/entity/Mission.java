package com.example.springstudy.domain.mission.entity;


import com.example.springstudy.domain.review.entity.Review;
import com.example.springstudy.domain.shop.entity.Shop;
import com.example.springstudy.global.entity.BaseEntity;
import com.example.springstudy.mapping.UserMission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_score")
    private int missionScore;

    @Column(name = "mission_time")
    private LocalDateTime missionTime;

    @Column(name = "mission_req")
    private int missionReq;

    @OneToMany(mappedBy = "mission", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserMission> userMissionList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}

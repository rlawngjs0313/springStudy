package com.example.springstudy.global.mapping;


import com.example.springstudy.domain.mission.entity.Mission;
import com.example.springstudy.domain.mission.enums.MissionCurrent;
import com.example.springstudy.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "user_mission")
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_current")
    @Enumerated(EnumType.STRING)
    private MissionCurrent missionCurrent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

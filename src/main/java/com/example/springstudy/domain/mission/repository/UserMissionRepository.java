package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.global.mapping.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Optional<UserMission> findByUserIdAndMissionId(Long userId, Long missionId);
}

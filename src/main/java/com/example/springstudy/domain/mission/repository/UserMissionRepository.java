package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.global.mapping.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}

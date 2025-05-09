package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.domain.mission.entity.Mission;
import com.example.springstudy.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}
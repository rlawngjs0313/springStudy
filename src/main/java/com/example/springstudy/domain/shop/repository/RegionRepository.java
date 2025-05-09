package com.example.springstudy.domain.shop.repository;

import com.example.springstudy.domain.shop.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findRegionByRegionName(String regionName);
    Optional<Region> findRegionById(Long id);
}

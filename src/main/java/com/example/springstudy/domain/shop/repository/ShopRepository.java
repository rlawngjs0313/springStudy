package com.example.springstudy.domain.shop.repository;

import com.example.springstudy.domain.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

}
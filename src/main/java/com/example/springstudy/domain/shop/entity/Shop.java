package com.example.springstudy.domain.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "shop_city")
    private String shopCity;
}

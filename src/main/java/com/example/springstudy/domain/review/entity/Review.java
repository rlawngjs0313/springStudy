package com.example.springstudy.domain.review.entity;

import com.example.springstudy.domain.shop.entity.Shop;
import com.example.springstudy.global.entity.BaseEntity;
import com.example.springstudy.mapping.UserReview;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "star")
    private int star;

    @Column(name = "review_content")
    private String reviewContent;

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserReview> userReviewList;

    @OneToOne(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private ReviewReply reviewReply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}

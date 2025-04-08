package com.example.springstudy.domain.user.entity;

import com.example.springstudy.domain.user.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "user_agree")
public class UserAgree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "term_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermName termName;

    @Column(name = "term_flag", length = 1, nullable = false)
    private int termFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

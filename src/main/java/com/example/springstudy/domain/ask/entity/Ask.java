package com.example.springstudy.domain.ask.entity;

import com.example.springstudy.domain.ask.enums.AnsFlag;
import com.example.springstudy.domain.ask.enums.AskType;
import com.example.springstudy.global.entity.BaseEntity;
import com.example.springstudy.mapping.UserAsk;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "ask")
public class Ask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ask_title", nullable = false)
    private String askTitle;

    @Column(name = "ans_flag")
    @Enumerated(EnumType.STRING)
    private AnsFlag ansFlag;

    @Column(name = "ask_type")
    @Enumerated(EnumType.STRING)
    private AskType askType;

    @Column(name = "ask_content", nullable = false)
    private String askContent;

    @OneToOne(mappedBy = "ask", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private AskAns askAns;

    @OneToMany(mappedBy = "ask", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<UserAsk> userAskList;
}

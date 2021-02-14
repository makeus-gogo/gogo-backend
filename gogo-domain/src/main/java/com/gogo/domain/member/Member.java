package com.gogo.domain.member;

import com.gogo.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String profileUrl;

    private String deviceToken;

    @Enumerated(EnumType.STRING)
    private MemberProvider provider;

    private int birthYear;

}

package com.account.api.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends CommonDateEntity {

    @Id
    @Column(name = "account_id", nullable = false, unique = true)
    private String accountId;

    @Column(unique = true)
    private String email;

    //미필수 컬럼
    private String imgUrl;
    private String name;
    private String birth;
    private String gender;
    private String introduce;

    //세팅 컬럼
    private String loginType; // (NAVER, GOOGLE... )소셜 로그인 타입
    private String openAt = "Y";    // (공계 Y / 미공개 N) 계정 공개여부 (기본값 공개)
    private String usedAt = "Y";    // (사용중 Y / 미사용 N)계정 사용여부 (기본값 사용중)

    //AccountGallery
    @Builder.Default
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Gallery> galleries = new ArrayList<>();

}

package com.account.api.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "follower")
public class Follower extends CommonDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "account_id", nullable = false)
   // private Account account;
    private String account;
   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "follower", nullable = false)
   // private Account follower;
    private String follower;

    private String confirm; // (승인 Y / 미승인 N)

}

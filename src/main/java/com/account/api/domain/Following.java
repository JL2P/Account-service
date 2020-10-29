package com.account.api.domain;

import io.swagger.annotations.BasicAuthDefinition;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "following")
public class Following extends CommonDateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "account_id", nullable = false)
    //private Account account;
    private String account;
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "following", nullable = false)
    //private Account following;
    private String following;

    private String confirm; // (승인 Y / 미승인 N)
}

package com.account.api.web.dto;

import com.account.api.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountModifyDto {
    // AccountModifyDto랑 프론트쪽으이 AccountModifyModel이랑 매핑이 되어야함
    private String accountId;
    private String email;
    private String name;
    private String birth;
    private String gender;
    private String introduce;

    public Account toEntity(Account account){
        account.setAccountId(this.accountId);
        account.setEmail(email);
        account.setName(name);
        account.setBirth(birth);
        account.setGender(gender);
        account.setIntroduce(introduce);

        return account;
    }
}

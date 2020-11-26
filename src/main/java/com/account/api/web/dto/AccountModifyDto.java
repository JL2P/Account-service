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
//    private String email;
    private String name;
    private String birth;
    private String gender;
    private String introduce;
    private String openAt;


    public Account toEntity(Account account){
        account.setAccountId(this.accountId);
        account.setImgUrl(account.getImgUrl());
        //이메일은 수정되면 안됨
        account.setEmail(account.getEmail());
        account.setName(this.name);
        account.setBirth(this.birth);
        account.setGender(this.gender);
        account.setIntroduce(this.introduce);
        account.setOpenAt(openAt);

        return account;
    }
}

package com.account.api.web.dto;

import com.account.api.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private String accountId;
    private String email;
    private String name;
    private String birth;
    private String gender;
    private String introduce;
    private String loginType;
    private String openAt;
    private String usedAt;

    public AccountDto(Account account){
        BeanUtils.copyProperties(account, this);
    }

    public Account toEntity(){
        Account account = new Account();
        BeanUtils.copyProperties(this,account);
        return account;
    }
}
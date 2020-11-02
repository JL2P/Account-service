package com.account.api.web.dto;

import com.account.api.domain.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Setter
@Getter
public class AccountAddDto {
    private String accountId;
    private String email;
    private String imgUrl;
    private String name;
    private String birth;
    private String gender;
    private String introduce;
    private String loginType;
    private String openAt;

    public Account toEntity(){
        Account account = new Account();
        BeanUtils.copyProperties(this,account);
        return account;
    }
}

package com.account.api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountSigninDto {
    String email;
    String password;
}

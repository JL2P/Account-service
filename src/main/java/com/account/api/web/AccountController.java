package com.account.api.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. Account"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts/") //컨트롤러 기본 URL
public class AccountController {
    //컨트롤러 구현 시작

}

package com.account.api.web;


import com.account.api.config.JwtTokenProvider;
import com.account.api.domain.Account;
import com.account.api.domain.service.AccountService;
import com.account.api.web.dto.AccountAddDto;
import com.account.api.web.dto.AccountDto;
import com.account.api.web.dto.AccountModifyDto;
import com.account.api.web.dto.AccountSigninDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Api(tags = {"1. Account"})
@RestController
@RequestMapping("/api/accounts") //컨트롤러 기본 URL
public class AccountController {
    //컨트롤러 구현 시작
    private final AccountService accountService;
    //jwt토큰을 decode하기 위함
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "유저 데이터 추가", notes = "회원가입시 인증서버에서 인증이 되었을 경우 유저 정보를 추가한다.")
    @PostMapping()
    public String addAccount(@RequestBody AccountDto accountDto) {
       accountService.addAccount(accountDto.toEntity());
        return "success";
    }

    @ApiOperation(value = "내 정보 조회", notes = "JWT에서 유저정보를 취득하여 유저정보를 불러온다")
    @GetMapping("/info")
    public AccountDto getMyInfo(HttpServletRequest request){
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String email = jwtTokenProvider.getAccountId(token);
        Account account = accountService.findByEmailAccount(email);
        return new AccountDto(account);
    }

    // account 목록
    @GetMapping()
    public List<AccountDto> getAccounts(){
        return accountService.getAccountList().stream().map(account->new AccountDto(account)).collect(Collectors.toList());
    }

    // account
    @GetMapping("/{accountId}")
    public AccountDto getAccount(@PathVariable String accountId) {
        return new AccountDto(accountService.getAccount(accountId));
    }

    // account 수정
    @PutMapping("/edit")
    public AccountDto modifyAccount(@RequestBody AccountModifyDto accountModifyDto){
        Account modifyAccount = accountService.getAccount(accountModifyDto.getAccountId());

        Account account = accountModifyDto.toEntity(modifyAccount);
        return new AccountDto(accountService.modifyAccount(account));
    }

    // account 삭제
    @DeleteMapping("/signout/{accountId}")
    public void deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
    }
}
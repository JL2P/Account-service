package com.account.api.web;

import com.account.api.domain.Account;
import com.account.api.domain.service.AccountService;
import com.account.api.web.dto.AccountAddDto;
import com.account.api.web.dto.AccountDto;
import com.account.api.web.dto.AccountModifyDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"1. Account"})
@RestController
@RequestMapping("/api/accounts/") //컨트롤러 기본 URL
public class AccountController {
    //컨트롤러 구현 시작
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 테스트를 위해 Account정보를 추가하는 기능 추가
    @PostMapping()
    public AccountAddDto addAccount(@RequestBody AccountAddDto accountAddDto){
        accountService.addAccount(accountAddDto.toEntity());
        return accountAddDto;
    }

    // account 목록
    @GetMapping()
    public List<AccountDto> getAccounts(){
        return accountService.getAccountList().stream().map(account->new AccountDto(account)).collect(Collectors.toList());
    }

    // account
    @GetMapping("{accountId}/")
    public AccountDto getAccount(@PathVariable String accountId) {
        return new AccountDto(accountService.getAccount(accountId));
    }

    // account 수정
    @PutMapping("edit/")
    public AccountDto modifyAccount(@RequestBody AccountModifyDto accountModifyDto){
        Account modifyAccount = accountService.getAccount(accountModifyDto.getAccountId());

        Account account = accountModifyDto.toEntity(modifyAccount);
        return new AccountDto(accountService.modifyAccount(account));
    }

    // account 삭제
    @DeleteMapping("signout/{accountId}/")
    public void deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
    }
}
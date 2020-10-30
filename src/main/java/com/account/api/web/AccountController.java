package com.account.api.web;

import com.account.api.domain.Account;
import com.account.api.domain.service.AccountService;
import com.account.api.web.dto.AccountModifyDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. Account"})
@RestController
@RequestMapping("/api/accounts/") //컨트롤러 기본 URL
public class AccountController {
    //컨트롤러 구현 시작
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // account 목록
    @GetMapping()
    public List<Account> getAccounts(){
        return accountService.getAccountList();
    }

    // account
    @GetMapping("{accountId}/")
    public Account getAccount(@PathVariable String accountId) {
        return accountService.getAccount(accountId);
    }

    // account 수정
    @PutMapping("edit/")
    public Account modifyAccount(@RequestBody AccountModifyDto accountModifyDto){
        Account modifyAccount = accountService.getAccount(accountModifyDto.getAccountId());

        Account account = accountModifyDto.toEntity(modifyAccount);
        return accountService.modifyAccount(account);
    }

    // account 삭제
    @DeleteMapping("signout/{accountId}/")
    public void deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
    }
}
package com.account.api.web;

import com.account.api.domain.Account;
import com.account.api.domain.service.AccountService;
import com.account.api.web.dto.AccountDto;
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

    @GetMapping()
    public List<Account> getAccounts(){
        return accountService.getAccountList();
    }

    @GetMapping("{accountId}/")
    public Account getAccount(@PathVariable String accountId) {
        return accountService.getAccount(accountId);
    }

    @PutMapping("edit/")
    public Account modifyAccount(@RequestBody AccountDto accountDto){
        Account account = accountDto.toEntity();
        return accountService.modifyAccount(account);
    }

    @DeleteMapping("signout/{accountId}/")
    public void deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
    }
}
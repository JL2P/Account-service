package com.account.api.domain.service;

import com.account.api.domain.Account;

import java.util.List;

public interface AccountService {
    //Account Service의 기능을 정의
    public List<Account> getAccountList();
    public Account getAccount(String accountId);
    public Account modifyAccount(Account account);
    public void deleteAccount(String accountId);


}
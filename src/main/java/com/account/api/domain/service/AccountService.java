package com.account.api.domain.service;

import com.account.api.domain.Account;

import java.util.List;

public interface AccountService {
    // 임시로 사용할 회원정보 추가하는 기능 ----------------
    public void addAccount(Account account);
    public Account findByEmailAccount(String email);
    //----------------------------------------------------

    //Account Service의 기능을 정의
    public List<Account> getAccountList();
    public Account getAccount(String accountId);
    public Account modifyAccount(Account account);
    public void deleteAccount(String accountId);


}
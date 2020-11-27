package com.account.api.domain.logic;

import com.account.api.domain.Account;
import com.account.api.domain.service.AccountService;
import com.account.api.exception.AccountNotExistException;
import com.account.api.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    //AccountService를 구현해야함


    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void addAccount(Account account) {
        if(isExist(account.getAccountId()))throw new AccountNotExistException(account.getAccountId().toString());
        accountRepository.save(account);
    }

    @Override
    public Account findByEmailAccount(String email) throws NoSuchElementException{
        return accountRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("account not found"));
    }

    @Override
    public List<Account> getAccountList(){
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    @Override
    public Account getAccount(String accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        return account;
    }

    @Override
    public Account modifyAccount(Account account) {
        if(isExist(account.getAccountId()))throw new AccountNotExistException(account.getAccountId().toString());
        accountRepository.save(account);
        return account;
    }

    @Override
    public void deleteAccount(String accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public boolean isExist(String  accountId) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        //Optional안에 todo객체가 존재하는 경우
        if(accountOpt.isPresent()) return true;

        //Optional안에 todo객체가 존재하지 않는 경우
        return false;
    }

}
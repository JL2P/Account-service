package com.account.api.repository;

import com.account.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
    public Account findByEmail(String email);
}

package com.account.api.repository;

import com.account.api.domain.Account;
import com.account.api.domain.Follower;
import com.account.api.domain.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowerRepository  extends JpaRepository<Follower,Long> {

    Follower findByAccountAndFollower(Account account, Account follower);
    List<Follower> findByAccount(Account account);

}

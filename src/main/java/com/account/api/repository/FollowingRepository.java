package com.account.api.repository;


import com.account.api.domain.Account;
import com.account.api.domain.Follower;
import com.account.api.domain.Following;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowingRepository extends JpaRepository<Following,Long> {
    Optional<Following> findByAccountAndFollowing(Account account, Account following);
    Optional<Following> findByAccountAndFollowingAndConfirm(Account account, Account following,String confirm);
    List<Following> findByAccount (Account account);

    List<Following> findByAccountAndConfirm(Account account, String confirm);

}

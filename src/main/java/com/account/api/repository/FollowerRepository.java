package com.account.api.repository;

import com.account.api.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowerRepository  extends JpaRepository<Follower,Long> {

    Follower findByAccountAndFollower(String account, String follower);
    Follower findByFollowerList (String account, String openAt);

}

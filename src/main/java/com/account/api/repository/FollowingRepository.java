package com.account.api.repository;

import com.account.api.domain.Follower;
import com.account.api.domain.Following;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepository extends JpaRepository<Following,Long> {
    Following findByAccountAndFollowing(String account, String following);
    Following findByFollowingList (String account, String openAt);
}

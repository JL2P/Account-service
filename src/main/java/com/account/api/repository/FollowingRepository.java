package com.account.api.repository;

import com.account.api.domain.Following;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepository extends JpaRepository<Following,Long> {
}

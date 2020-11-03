package com.account.api.domain.service;

import com.account.api.domain.Account;
import com.account.api.domain.Following;

import java.util.List;

public interface FollowService {
    //팔로우 기능
    public void follow(String myAccountId1, String myAccountId2);

    public void accept(String myAccountId1, String myAccountId2);
    public void refuse(String myAccountId1, String myAccountId2);
    public List<Following> getFollowings(String myAccountId);
}

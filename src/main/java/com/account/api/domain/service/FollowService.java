package com.account.api.domain.service;

import com.account.api.domain.Account;
import com.account.api.domain.Following;
import com.account.api.exception.FollowCheckException;

import java.util.List;

public interface FollowService {
    //나랑 팔로우관계인지 체크하는 함수.
    public boolean followCheck(String myAccountId1, String myAccountId2) throws FollowCheckException;
    //팔로우 기능
    public void follow(String myAccountId1, String myAccountId2);

    public void accept(String myAccountId1, String myAccountId2);
    public void refuse(String myAccountId1, String myAccountId2);
    public List<Following> getFollowings(String myAccountId);
}

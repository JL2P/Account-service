package com.account.api.domain.service;

public interface FollowService {
    //팔로우 기능
    public void follow(String account_id1, String account_id2, String myopenAt, String openAt);
}

package com.account.api.domain.service;

public interface FollowService {
    //팔로우 기능
    public void follow(String account_id1, String myopenAt, String account_id2, String openAt);

    public void accept(String myAccountId, String followAccountId);
}

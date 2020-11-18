package com.account.api.domain.service;

import com.account.api.domain.Account;
import com.account.api.domain.Follower;
import com.account.api.domain.Following;
import com.account.api.exception.FollowCheckException;
import com.account.api.exception.FollowerCheckException;
import com.account.api.exception.FollowingCheckException;
import com.account.api.web.dto.AccountDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface FollowService {

    //confirm 받지 않은 나의 팔로워들 조회하는 함수
    public List<Account>  getAllFollowers(String accountId) throws NoSuchElementException;
    //나랑 팔로우관계인지 체크하는 함수.
    public boolean followCheck(String myAccountId1, String myAccountId2) throws FollowCheckException;
    //내가 팔로잉하고 있는지 체크하는 함수.
    public boolean followingCheck(String accountId1, String accountId2) throws FollowingCheckException;
    //나를 팔로잉하고 있는지 체크하는 함수(=나의 팔로워인지 체크하는 함수)
    public boolean followerCheck(String accountId1, String accountId2) throws FollowerCheckException;
    //팔로우 기능
    public void follow(String myAccountId1, String myAccountId2);
    //팔로잉신청 수락 기능
    public void accept(String myAccountId1, String myAccountId2);
    //팔로잉신청 거절 / 팔로워리스트에서 팔로워 삭제 기능
    public void refuse(String myAccountId1, String myAccountId2);
    //팔로잉리스트에서 팔로잉 삭제 기
    public void deleteMyfollowing(String accountId1, String accountId2);
    public List<Following> getFollowings(String myAccountId) throws NoSuchElementException;
    //승훈 추가
    public List<Following> getMyFollowings(String accountId) throws NoSuchElementException;
    //
    //팔로워리스트 조회기능
    public List<Account> getMyFollowers(String myAccountId) throws NoSuchElementException;
    //

    public int getNumberOfMyFollowings(String myAccountId) throws NoSuchElementException;
    public int getNumberOfMyFollowers (String myAccountId) throws NoSuchElementException;
}

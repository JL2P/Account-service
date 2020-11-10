package com.account.api.domain.logic;

import com.account.api.domain.Account;
import com.account.api.domain.Follower;
import com.account.api.domain.Following;
import com.account.api.domain.service.FollowService;
import com.account.api.exception.FollowCheckException;
import com.account.api.repository.AccountRepository;
import com.account.api.repository.FollowerRepository;
import com.account.api.repository.FollowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FollowServiceImpl implements FollowService {

    private final FollowerRepository followerRepository;
    private final FollowingRepository followingRepository;
    private final AccountRepository accountRepository;

    @Override
    public boolean followCheck(String accountId1, String accountId2) throws FollowCheckException {
        Account account1 = accountRepository.findById(accountId1).orElseThrow();
        Account account2 = accountRepository.findById(accountId2).orElseThrow();

        System.out.println(accountId1+" "+accountId2);
        if (followerRepository.findByAccountAndFollower(account2, account1).isPresent())
            return true;

        return false;
    }


    @Override
    public void follow(String accountId1, String accountId2) throws FollowCheckException {

        Account account1 = accountRepository.findById(accountId1).orElseThrow();
        Account account2 = accountRepository.findById(accountId2).orElseThrow();

        if (followerRepository.findByAccountAndFollower(account2, account1).isPresent()) {
            throw new FollowCheckException("follow is exist");
        }
        else {
            //여기다가 실제 팔로우 되는 기능을 구현한다.
            Follower follower = Follower.builder()
                    .account(account2)
                    .follower(account1)
                    .confirm("N")
                    .build();

            Following following = Following.builder()
                    .account(account1)
                    .following(account2)
                    .confirm("N").build();

            // 공개계정일경우 자동으로 승인처리
            if (account1.getOpenAt() != null && account1.getOpenAt().equals("Y")) {
                follower.setConfirm("Y");
            }
            if (account2.getOpenAt() != null && account2.getOpenAt().equals("Y")) {
                following.setConfirm("Y");
            }
            followerRepository.save(follower);
            followingRepository.save(following);
        }
    }


    // Follow관련 서비스 구현
    /*
     * 하트 부분에서 승인하는거
     * */

    //팔로우 요청 승인
    public void accept(String accountId, String followerId) {

        Account account = accountRepository.findById(accountId).orElseThrow();
        Account follower = accountRepository.findById(followerId).orElseThrow();

        //orElseThrow() => 객체가 있으면 객체반환 없으면 익셉션(예외처리) 실행.
        Follower findFollower = followerRepository.findByAccountAndFollower(account, follower).orElseThrow(()-> new NoSuchElementException());
        Following findFollowing = followingRepository.findByAccountAndFollowing(findFollower.getFollower(), findFollower.getAccount()).orElseThrow(()->new NoSuchElementException());
        findFollower.setConfirm("Y");
        findFollowing.setConfirm("Y");

        followerRepository.save(findFollower);
        followingRepository.save(findFollowing);
    }

    //팔로우 요청 거절
    public void refuse(String accountId, String followerId) {

        Account account = accountRepository.findById(accountId).orElseThrow();
        Account follower = accountRepository.findById(followerId).orElseThrow();

        Follower findFollower = followerRepository.findByAccountAndFollower(account, follower).orElseThrow(()-> new NoSuchElementException());
        Following findFollowing = followingRepository.findByAccountAndFollowing(findFollower.getFollower(), findFollower.getAccount()).orElseThrow(()-> new NoSuchElementException());

        followerRepository.delete(findFollower);
        followingRepository.delete(findFollowing);

    }

//    나의 팔로잉 리스트 전체 조회
    public List<Following> getFollowings(String accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();

        List<Following> allFollowings = followingRepository.findByAccount(account);
        List<Following> findFollowings = new ArrayList<>();

        for (int i = 0; i < allFollowings.size(); i++) {
            Following following = followingRepository.findByAccountAndFollowing(account, allFollowings.get(i).getFollowing()).orElseThrow(()-> new NoSuchElementException());
            if (following.getConfirm().equals("Y")) {
                findFollowings.add(following);
            }
        }
        return findFollowings;

    }

    // 내가 팔로잉 하는 사람 수 조회
    public int getNumberOfMyFollowings (String accountId) {
        List<Following> followingList = getFollowings(accountId);
        int total_followings = followingList.size();

        return total_followings;
    }


    //나의 팔로워 리스트 전체 조회
    public List<Follower> getFollowers(String accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();

        List<Follower> allFollowers = followerRepository.findByAccount(account);
        List<Follower> findFollowers = new ArrayList<>();

        for (int i = 0; i < allFollowers.size(); i++) {
            Follower follower = followerRepository.findByAccountAndFollower(account, allFollowers.get(i).getFollower()).orElseThrow(()-> new NoSuchElementException());
            if (follower.getConfirm().equals("Y")) {
                findFollowers.add(follower);
            }
        }
        return findFollowers;
    }

    // 나를 팔로워 하는 사람 수 조회
    public int getNumberOfMyFollowers (String accountId) {
        List<Follower> followerList = getFollowers(accountId);
        int total_followers = followerList.size();
        return total_followers;
    }


}
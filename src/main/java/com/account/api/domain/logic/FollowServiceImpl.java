package com.account.api.domain.logic;

import com.account.api.domain.Account;
import com.account.api.domain.Follower;
import com.account.api.domain.Following;
import com.account.api.domain.service.FollowService;
import com.account.api.repository.FollowerRepository;
import com.account.api.repository.FollowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class FollowServiceImpl implements FollowService {

    private final FollowerRepository followerRepository;
    private final FollowingRepository followingRepository;

    public FollowServiceImpl(FollowerRepository followerRepository, FollowingRepository followingRepository) {
        this.followerRepository = followerRepository;
        this.followingRepository = followingRepository;
    }

    @Override
    public void follow(String account_id1, String myopenAt, String account_id2, String openAt) {

        //여기다가 실제 팔로우 되는 기능을 구현한다.
        Follower follower = Follower.builder()
                .account(account_id2)
                .follower(account_id1)
                .confirm("N")
                .build();

        Following following = Following.builder()
                .account(account_id1)
                .following(account_id2)
                .confirm("N").build();

        // 공개계정일경우 자동으로 승인처리
        if(myopenAt.equals("Y")){
            follower.setConfirm("Y");
        }
        if(openAt.equals("Y")) {
            following.setConfirm("Y");
        }

        followerRepository.save(follower);
        followingRepository.save(following);

    }


    // Follow관련 서비스 구현
    /*
    * 하트 부분에서 승인하는거
    * */

    //팔로우 요청 승인
    public void accept(String accountId, String followerId) {

        //orElseThrow() => 객체가 있으면 객체반환 없으면 익셉션(예외처리) 실행.
        Follower findFollower = followerRepository.findByAccountAndFollower(accountId,followerId);
        Following findFollowing =followingRepository.findByAccountAndFollowing(findFollower.getFollower(), findFollower.getAccount());

        findFollower.setConfirm("Y");
        findFollowing.setConfirm("Y");

        followerRepository.save(findFollower);
        followingRepository.save(findFollowing);
    }

    //팔로우 요청 거절
    public void refuse(String accountId, String followerId) {

        Follower findFollower = followerRepository.findByAccountAndFollower(accountId,followerId);
        Following findFollowing =followingRepository.findByAccountAndFollowing(findFollower.getFollower(), findFollower.getAccount());

        followerRepository.delete(findFollower);
        followingRepository.delete(findFollowing);

    }




}


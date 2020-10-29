package com.account.api.domain.logic;

import com.account.api.domain.Account;
import com.account.api.domain.Follower;
import com.account.api.domain.Following;
import com.account.api.domain.service.FollowService;
import com.account.api.repository.FollowerRepository;
import com.account.api.repository.FollowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowServiceImpl implements FollowService {

    private final FollowerRepository followerRepository;
    private final FollowingRepository followingRepository;

    public FollowServiceImpl(FollowerRepository followerRepository, FollowingRepository followingRepository) {
        this.followerRepository = followerRepository;
        this.followingRepository = followingRepository;
    }

    @Override
    public void follow(String account_id1, String account_id2, String myopenAt, String openAt) {

        //여기다가 실제 팔로우 되는 기능을 구현한다.
        Follower follower = Follower.builder()
                .account(account_id1)
                .follower(account_id2)
                .confirm("N")
                .build();

        Following following = Following.builder()
                .account(account_id2)
                .following(account_id1)
                .confirm("N").build();

        //  i cant hear anything...
        //open check
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
    public void addFollower(Follower follower, Long myId, Long followId) {

        //orElseThrow() => 객체가 있으면 객체반환 없으면 익셉션(예외처리) 실행.
        Follower findFollower = followerRepository.findById(follower.getId()).orElseThrow();
        //팔로우 요청을 승인했으면
        if(findFollower.getConfirm() == "Y") {
        //팔로워 리스트에 추가
        followerId
        }
        //

    }





}


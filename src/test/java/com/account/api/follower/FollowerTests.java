package com.account.api.follower;

import com.account.api.domain.Follower;
import com.account.api.domain.Following;
import com.account.api.repository.FollowerRepository;
import com.account.api.repository.FollowingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
public class FollowerTests {

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private FollowingRepository followingRepository;

    @Before
    public void setUp() {
        //A의 입장 A가 B를 팔로우한다
        String account_id1 = "TA";
        String account_id2 = "TB";

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

        followerRepository.save(follower);
        followingRepository.save(following);
    }

    @Test
    public void 팔로우_데이터를_잘가져오는_test() {
        //B의 입장 B가 A를 승인한다.

        String accountId = "TB";
        String followerId = "TA";

        //when
        //값이 디비에서 잘넘어오는지 테스트
        Following findFollowing = followingRepository.findByAccountAndFollowing(followerId, accountId);
        Follower findFollower = followerRepository.findByAccountAndFollower(accountId, followerId);

        //then
        assertThat(findFollowing.getAccount()).isEqualTo(followerId);
        assertThat(findFollower.getAccount()).isEqualTo(accountId);
    }

    @Test
    public void 팔로우요청_승인을_잘처리하는지_test() {

        String accountId = "TB";
        String followerId = "TA";

        //when
        Follower findFollower = followerRepository.findByAccountAndFollower(accountId, followerId);
        Following findFollowing = followingRepository.findByAccountAndFollowing(findFollower.getFollower(), findFollower.getAccount());


        findFollower.setConfirm("Y");
        findFollowing.setConfirm("Y");

        //then
        assertThat(findFollowing.getConfirm()).isEqualTo("Y");
        assertThat(findFollower.getConfirm()).isEqualTo("Y");

    }

    @Test
    public void 팔로우요청_거절을_잘처리하는지_Test() {

        String accountId = "B";
        String followerId = "A";

        //when
        Follower findFollower = followerRepository.findByAccountAndFollower(accountId, followerId);
        Following findFollowing = followingRepository.findByAccountAndFollowing(findFollower.getFollower(), findFollower.getAccount());

        followerRepository.delete(findFollower);
        followingRepository.delete(findFollowing);

        //then
        Follower deletedFollower = followerRepository.findByAccountAndFollower(accountId, followerId);

        assertThat(ObjectUtils.isEmpty(deletedFollower)).isEqualTo(true);
        assertThat(findFollower.getConfirm()).isEqualTo("Y");


    }

    @Test
    public void 나의_팔로잉리스트를_잘조회히는지_Test() {

        String accountId = "TA";


        //when
        List<Following> allFollowings = followingRepository.findByAccount(accountId);
        System.out.println(allFollowings);
        List<Following> findFollowings = new ArrayList<>();
        assertThat(allFollowings.get(0).getConfirm()).isEqualTo("N");
        assertThat(allFollowings.get(0).getAccount()).isEqualTo("TA");


//        for (int i = 0; i < allFollowings.size(); i++) {
//            Following following = followingRepository.findByAccountAndFollowing(accountId, allFollowings.get(i).getFollowing());
//            if (following.getConfirm().equals("Y")) {
//                findFollowings.add(following);
//            }
//        }

    }

}

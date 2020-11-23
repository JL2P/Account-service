package com.account.api.web;

import com.account.api.config.JwtTokenProvider;
import com.account.api.domain.Account;
import com.account.api.domain.Following;
import com.account.api.domain.service.AccountService;
import com.account.api.domain.service.FollowService;
import com.account.api.exception.FollowCheckException;
import com.account.api.exception.FollowerCheckException;
import com.account.api.exception.FollowingCheckException;
import com.account.api.web.dto.AccountDto;
import com.account.api.web.dto.FollowDto;
import com.account.api.web.dto.FollowStateDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"1. Follower"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts/follow/") //컨트롤러 기본 URL
public class FollowController {

    private final FollowService followService;
    private final AccountService accountService;
    private final JwtTokenProvider jwtTokenProvider;
    //팔로우 체크
    @PostMapping("/isfollow/{followId}")
    public FollowStateDto followCheck(@PathVariable String followId, HttpServletRequest request)throws FollowCheckException {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        return new FollowStateDto(followService.followCheck(accountId, followId));
    }

    @PostMapping("/isfollowing/{followId}")
    public FollowStateDto followingCheck(@PathVariable String followId, HttpServletRequest request)throws FollowingCheckException {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        return new FollowStateDto(followService.followingCheck(accountId, followId));
    }

    @PostMapping("/isfollowingPage/{followId}")
    public FollowStateDto followingPageCheck(@PathVariable String followId, HttpServletRequest request)throws FollowingCheckException {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        return new FollowStateDto(followService.followingCheck(accountId, followId));
    }

    //팔로워인지 체크
    @PostMapping("isfollower/{followId}")
    public FollowStateDto followerCheck (@PathVariable String followId, HttpServletRequest request) throws FollowerCheckException {
        String token = jwtTokenProvider.resolveToken(request);
        String accountId = jwtTokenProvider.getAccountId(token);

        return new FollowStateDto(followService.followerCheck(accountId, followId));

    }





    @PostMapping("")
    public void follow(@RequestBody FollowDto followDto) throws FollowCheckException{
        // make follow function


    followService.follow(followDto.getMyAccountId(), followDto.getFollowAccountId());

    }

    //아직 나에게 confirm 받지 않은 나의 팔로워 리스트 조회
    @GetMapping("")
    public List<AccountDto> getAllFollowers(HttpServletRequest request) {

        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);
        List<Account> followers = followService.getAllFollowers(accountId);
        return followers.stream().map(follow -> new AccountDto(follow)).collect(Collectors.toList());
    }



    //post 는 생성 put 은 수정을 많이 한다.
    @PutMapping("/confirm/{followId}")
    public void confirm(@PathVariable String followId, HttpServletRequest request) {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        followService.accept(accountId, followId);
    }

    @DeleteMapping("/refuse/{followId}")
    public void refuse(@PathVariable String followId, HttpServletRequest request) {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        followService.refuse(accountId, followId);

    }

    @DeleteMapping("/delete/{followId}")
    public void deleteMyfollowing(@PathVariable String followId, HttpServletRequest request) {
        //토큰에서 내 정보 추출
        String token = jwtTokenProvider.resolveToken(request);
        String accountId = jwtTokenProvider.getAccountId(token);

        followService.deleteMyfollowing(accountId, followId);

    }

    @GetMapping("/myFollowerList")
    public List<AccountDto> getMyFollowers(HttpServletRequest request) {

        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);
        List<Account> followers = followService.getMyFollowers(accountId);
        return followers.stream().map(follow -> new AccountDto(follow)).collect(Collectors.toList());
    }


    @GetMapping("/followingList")
    public void getFollowings(@RequestBody FollowDto followDto) {

        followService.getFollowings(followDto.getMyAccountId());
    }

    //승훈 생성
    //내가 팔로우를 신청한 사람들중에 나를 승인한 사람들의 데이터를 보내준다.
    @GetMapping("/myFollowingList")
    public List<AccountDto> getMyFollowings(HttpServletRequest request) {
        //토큰에서 내 정보 추출
        String token = jwtTokenProvider.resolveToken(request);
        String accountId = jwtTokenProvider.getAccountId(token);
        //내가 팔로우를 신청한 사람들 중 나를 승인한 사람들 (Following테이블에서 가져오기)
        List<Following> followings = followService.getMyFollowings(accountId);
        //AccountDto로 변환하여 리턴
        return followings.stream().map(following ->
                new AccountDto(following.getFollowing())).collect(Collectors.toList());
    }
    //


//    @GetMapping("/myFollowerCnt")
//    public int getMyFollowerCnt (HttpServletRequest request) {
//        List<AccountDto> myFollowerList = getMyFollowers(request);
//        int cnt=0;
//        for(int i=0; i<myFollowerList.size(); i++) cnt++;
//
//        return cnt;
//    }

//    @GetMapping("/myFollowingCnt")
//    public int getMyFollowingCnt (HttpServletRequest request) {
//        List<AccountDto> myFollowingList = getMyFollowings(request);
//        int cnt=0;
//        for(int i=0; i<myFollowingList.size(); i++) cnt++;
//
//        return cnt;
//    }

    // 승훈 추가
    // 기존에 token에서 account정보를 가지고 오는 함수(getMyFollowers)를 url에서 가지고 오도록 변경된 함수
    @GetMapping("{accountId}/followers")
    public List<AccountDto> getFollowers(@PathVariable String accountId) {
        List<Account> followers = followService.getMyFollowers(accountId);
        return followers.stream().map(follow -> new AccountDto(follow)).collect(Collectors.toList());
    }

    // 기존에 token에서 account정보를 가지고 오는 함수(getMyFollowings)를 url에서 가지고 오도록 변경된 함수
    @GetMapping("{accountId}/followings")
    public List<AccountDto> getFollowings(@PathVariable String accountId) {
        //내가 팔로우를 신청한 사람들 중 나를 승인한 사람들 (Following테이블에서 가져오기)
        List<Following> followings = followService.getMyFollowings(accountId);
        return followings.stream().map(following ->
                new AccountDto(following.getFollowing())).collect(Collectors.toList());
    }


    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    ErrorMessage runTimeError(RuntimeException e) {
        ErrorMessage error = new ErrorMessage();
        error.setMessage(error.getMessage());
        return error;
    }




}

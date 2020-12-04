package com.account.api.web;

import com.account.api.config.JwtTokenProvider;
import com.account.api.domain.Account;
import com.account.api.domain.Follower;
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
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @ApiOperation(value = "팔로우 체크", notes = "url에서 취득한 예비 팔로워 정보와 JWT에서 취득한 내 정보를 팔로워 테이블에서 일치하는 데이터가 있는지 확인한다.")
    @PostMapping("/isfollow/{followId}")
    public FollowStateDto followCheck(@PathVariable String followId, HttpServletRequest request)throws FollowCheckException {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        return new FollowStateDto(followService.followCheck(accountId, followId));
    }
    @ApiOperation(value = "팔로잉 체크", notes = "나를 팔로잉하고 있는지(나의 팔로워리스트에 포함) 확인한다.")
    @PostMapping("/isfollowing/{followId}")
    public FollowStateDto followingCheck(@PathVariable String followId, HttpServletRequest request)throws FollowingCheckException {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        return new FollowStateDto(followService.followingCheck(accountId, followId));
    }

    @ApiOperation(value = "팔로잉페이지 체크", notes = "팔로잉하고 있는 계정의 프로필페이지인지 확인한다")
    @PostMapping("/isfollowingPage/{followId}")
    public FollowStateDto followingPageCheck(@PathVariable String followId, HttpServletRequest request)throws FollowingCheckException {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        return new FollowStateDto(followService.followingCheck(accountId, followId));
    }
    @ApiOperation(value = "팔로워 체크", notes = "내가 팔로잉하고 있는지(나의 팔로잉리스트에 포함) 확인한다.")
    //팔로워인지 체크
    @PostMapping("isfollower/{followId}")
    public FollowStateDto followerCheck (@PathVariable String followId, HttpServletRequest request) throws FollowerCheckException {
        String token = jwtTokenProvider.resolveToken(request);
        String accountId = jwtTokenProvider.getAccountId(token);

        return new FollowStateDto(followService.followerCheck(accountId, followId));

    }

//    @ApiOperation(value = "팔로워 체크", notes = "나를 팔로잉하고 있는지(나의 팔로워리스트에 포함) 확인한다.")
//    @PostMapping("isfollowers")
//    public List<FollowStateDto> followerListCheck (@RequestBody ArrayList<FollowDto> followDtos, HttpServletRequest request) throws FollowerCheckException {
//        String token = jwtTokenProvider.resolveToken(request);
//        String accountId = jwtTokenProvider.getAccountId(token);
//
//        List<Follower> followers = followDtos.stream().map(followDto->followService.followerCheck(followDto.getMyAccountId(), followDto.getFollowAccountId())).collect(Collectors.toList());
//
//        return new FollowStateDto(followService.followerCheck(accountId, followId));
//
//}


    @ApiOperation(value = "계정 팔로우", notes = "팔로잉하고 싶은 계정을 팔로잉, 팔로워 테이블에 추가한다")
    @PostMapping("")
    public void follow(@RequestBody FollowDto followDto) throws FollowCheckException{
        // make follow function


    followService.follow(followDto.getMyAccountId(), followDto.getFollowAccountId());

    }

    //아직 나에게 confirm 받지 않은 나의 팔로워 리스트 조회
    @ApiOperation(value = "아직 나에게 confirm받지 않은 나의 예비 팔로워 리스트 조회")
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
    @ApiOperation(value = "팔로잉 신청 수락", notes = "팔로잉 요청 수락하면 confirm 상태를 업데이트하여 나의 팔로워 리스트, 상대방의 팔로잉 리스트에 추가한")
    @PutMapping("/confirm/{followId}")
    public void confirm(@PathVariable String followId, HttpServletRequest request) {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        followService.accept(accountId, followId);
    }
    @ApiOperation(value = "팔로잉 신청 거절")
    @DeleteMapping("/refuse/{followId}")
    public void refuse(@PathVariable String followId, HttpServletRequest request) {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        followService.refuse(accountId, followId);

    }
    @ApiOperation(value = "나의 팔로워 삭제", notes = "나를 팔로잉하는 사람을 삭제한다")
    @DeleteMapping("/delete/{followId}")
    public void deleteMyfollowing(@PathVariable String followId, HttpServletRequest request) {
        //토큰에서 내 정보 추출
        String token = jwtTokenProvider.resolveToken(request);
        String accountId = jwtTokenProvider.getAccountId(token);

        followService.deleteMyfollowing(accountId, followId);

    }
    @ApiOperation(value = "나의 팔로워리스트 조회")
    @GetMapping("/myFollowerList")
    public List<AccountDto> getMyFollowers(HttpServletRequest request) {

        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);
        List<Account> followers = followService.getMyFollowers(accountId);
        return followers.stream().map(follow -> new AccountDto(follow)).collect(Collectors.toList());
    }

    @ApiOperation(value = "나의 팔로잉 리스트 조회")
    @GetMapping("/followingList")
    public void getFollowings(@RequestBody FollowDto followDto) {

        followService.getFollowings(followDto.getMyAccountId());
    }

    //승훈 생성
    //내가 팔로우를 신청한 사람들중에 나를 승인한 사람들의 데이터를 보내준다.
    @ApiOperation(value = "팔로잉 체크")
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
    @ApiOperation(value = "url에서 account정보 가져옴", notes = "기존에 token에서 account정보를 가지고 오는 함수(getMyFollowers)를 url에서 가지고 오도록 변경된 함수")
    @GetMapping("{accountId}/followers")
    public List<AccountDto> getFollowers(@PathVariable String accountId) {
        List<Account> followers = followService.getMyFollowers(accountId);
        return followers.stream().map(follow -> new AccountDto(follow)).collect(Collectors.toList());
    }

    // 기존에 token에서 account정보를 가지고 오는 함수(getMyFollowings)를 url에서 가지고 오도록 변경된 함수
    @ApiOperation(value = "url에서 account정보 가져옴", notes ="기존에 token에서 account정보를 가지고 오는 함수(getMyFollowings)를 url에서 가지고 오도록 변경된 함수" )
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

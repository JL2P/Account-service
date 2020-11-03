package com.account.api.web;

import com.account.api.domain.service.AccountService;
import com.account.api.domain.service.FollowService;
import com.account.api.web.dto.AccountDto;
import com.account.api.web.dto.FollowDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. Follower"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts") //컨트롤러 기본 URL
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public void follow(@RequestParam String accountId1, @RequestParam String accountId2){
        // make follow function
        followService.follow(accountId1,accountId2);
    }
    //post 는 생성 put 은 수정을 많이 한다.
//    @PutMapping("/follow")
//    public void confirm(AccountDto) {
//         // make confirm function
//
//        String myAccountId = followDto.getMyAccountId();
//        String followAccountId = followDto.getFollowAccountId();
//
//        followService.accept(myAccountId, followAccountId);
//    }
//    @DeleteMapping("/follow")
//    public void refuse() {
//
//        followService.refuse(myAccountId, followAccountId);
//
//    }

}

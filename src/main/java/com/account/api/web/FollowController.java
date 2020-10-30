package com.account.api.web;

import com.account.api.domain.service.FollowService;
import com.account.api.web.dto.FollowDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. Account"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts") //컨트롤러 기본 URL
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public void follow(@RequestBody FollowDto followDto){
        // make follow function

        String myAccountId = followDto.getMyAccountId();
        String myOpenAt = followDto.getMyOpenAt();
        String followAccountId = followDto.getFollowAccountId();
        String followOpenAt = followDto.getFollowOpenAt();

        followService.follow(myAccountId,myOpenAt,followAccountId,followOpenAt);
    }
    //post 는 생성 put 은 수정을 많이 한다.
    @PutMapping("/follow")
    public void confirm(@RequestBody FollowDto followDto) {
         // make confirm function

        String myAccountId = followDto.getMyAccountId();
        String followAccountId = followDto.getFollowAccountId();

        followService.accept(myAccountId, followAccountId);
    }
    @DeleteMapping("/follow")
    public void refuse(@RequestParam String myAccountId, @RequestParam String followAccountId) {

        followService.refuse(myAccountId, followAccountId);

    }

}

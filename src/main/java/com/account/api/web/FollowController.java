package com.account.api.web;

import com.account.api.domain.service.AccountService;
import com.account.api.domain.service.FollowService;
import com.account.api.exception.FollowCheckException;
import com.account.api.web.dto.AccountDto;
import com.account.api.web.dto.FollowDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Api(tags = {"1. Follower"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts") //컨트롤러 기본 URL
public class FollowController {

    private final FollowService followService;

    @PostMapping("/isfollow")
    public boolean followCheck(@RequestBody FollowDto followDto)throws FollowCheckException {
        return followService.followCheck(followDto.getMyAccountId(), followDto.getFollowAccountId());
    }

    @PostMapping("/follow")
    public void follow(@RequestBody FollowDto followDto) {
        // make follow function

    followService.follow(followDto.getMyAccountId(), followDto.getFollowAccountId());

    }
    //post 는 생성 put 은 수정을 많이 한다.
    @PutMapping("/follow")
    public void confirm(@RequestBody FollowDto followDto) {
         // make confirm function

        followService.accept(followDto.getMyAccountId(), followDto.getFollowAccountId());
    }

    @DeleteMapping("/follow")
    public void refuse(@RequestBody FollowDto followDto) {

        followService.refuse(followDto.getMyAccountId(), followDto.getFollowAccountId());

    }


    @GetMapping("/followers")
    public void getFollowings(@RequestBody FollowDto followDto) {

        followService.getFollowings(followDto.getMyAccountId());
    }

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    ErrorMessage runTimeError(RuntimeException e) throws FollowCheckException {
        ErrorMessage error = new ErrorMessage();
        error.setMessage("test");
        return error;
    }

}

package com.account.api.web;

import com.account.api.domain.service.FollowService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. Account"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts") //컨트롤러 기본 URL
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService follonmnjwService) {
        this.followService = followService;
    }


    @PostMapping("follow")
    public void follow(){
        // make follow function
        followService.follow();
    }
}

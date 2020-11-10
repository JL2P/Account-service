package com.account.api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FollowStateDto {
    private boolean followState;

    public FollowStateDto(boolean state){
        this.followState= state;
    }
}

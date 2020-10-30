package com.account.api.web.dto;

import com.account.api.domain.Follower;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Setter
@Getter
public class FollowDto {
    private String myAccountId;
    private String myOpenAt;
    private String followAccountId;
    private String followOpenAt;
}

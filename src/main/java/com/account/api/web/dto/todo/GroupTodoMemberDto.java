package com.account.api.web.dto.todo;

import lombok.Getter;

@Getter
public class GroupTodoMemberDto {
    private long groupTodoMemberId;
    private String attender;
    private String todoId;
    private long grouptodoId;
}

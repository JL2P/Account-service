package com.account.api.web.dto.todo;

import com.account.api.domain.Account;
import com.account.api.web.dto.AccountDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentDto {
    private Long commentId;
    private Long todoId;
    private String text;        // 내용
    private String writer;      // 작성자ID
    private String likePoint;   // 좋아요
    private String created;
    private List<SubCommentDto> subComments = new ArrayList<SubCommentDto>();
    private AccountDto accountModel;

    public void setAccountModel(Account account){
        this.accountModel = new AccountDto(account);
    }
}

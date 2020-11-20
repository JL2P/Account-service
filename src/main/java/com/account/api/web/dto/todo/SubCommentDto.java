package com.account.api.web.dto.todo;

import com.account.api.domain.Account;
import com.account.api.web.dto.AccountDto;
import lombok.Getter;
import lombok.Setter;


@Getter
public class SubCommentDto {
    private Long subCommentId;
    private Long commentId;   // CommentId
    private String targetId;    // 어떤 사람의 댓글인지
    private String text;        // 내용
    private String writer;      // 작성자ID
    private String likePoint;   // 좋아요
    private String created;
    private AccountDto accountModel;

    public void setAccountModel(Account account){
        this.accountModel = new AccountDto(account);
    }
}

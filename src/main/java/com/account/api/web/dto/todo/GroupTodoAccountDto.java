package com.account.api.web.dto.todo;


import com.account.api.domain.Account;
import com.account.api.web.dto.AccountDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 기존의 GroupTodo서비스에서 받아온 GroupTodo에 AccountModel항목이 추가된 DTO
 * GroupTodo객체의 writer를 통해서 AccountModel을 취득하여 넣어 준다.
 * 나머지는 기존의 GroupTodo와 동일하다.
 * */
@Getter
public class GroupTodoAccountDto {
    private long groupTodoId;            // Id
    private long groupId;
    private String imgUrl;      // 이미지 URL
    private String title;       // 제목
    private String description; // 설명글
    private String category;    // 카테고리
    private String writer;      // 작성자ID
    private int likePoint;      // 좋아요 갯수
    private boolean likeState;  // 좋아요 했는지 체크
    private List<GroupTodoMemberDto> members;
    private List<CommentDto> comments;
    private LocalDateTime created;
    private LocalDateTime modified;
    private AccountDto accountModel;

    public void setAccountModel(Account account){
        this.accountModel = new AccountDto(account);
    }
}

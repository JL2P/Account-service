package com.account.api.web.dto.todo;

import com.account.api.domain.Account;
import com.account.api.web.dto.AccountDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TodoAccountDto {
    private long todoId;            // Id
    private String imgUrl;      // 이미지 URL
    private String title;       // 제목
    private String description; // 설명글
    private String category;    // 카테고리
    private String writer;      // 작성자ID
    private String startTime;   // 시작일자
    private String endTime;     // 마감일자
    private String groupAt;     // 그룹계획여부
    private int likePoint;      // 좋아요 갯수
    private boolean likeState;  // 좋아요 했는지 체크
    private String completed;   // 계획 완료 여부 (완료:Y 미완료:N)
    private List<CommentDto> comments;
    private List<TodoGalleryDto> galleries; //이미지
    private LocalDateTime created;
    private LocalDateTime modified;
    private AccountDto accountModel;

    public void setAccountModel(Account account){
        this.accountModel = new AccountDto(account);
    }
}

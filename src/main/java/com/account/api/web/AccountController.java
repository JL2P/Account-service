package com.account.api.web;


import com.account.api.config.JwtTokenProvider;
import com.account.api.domain.Account;
import com.account.api.domain.service.AccountService;
import com.account.api.web.dto.AccountDto;
import com.account.api.web.dto.AccountModifyDto;

import com.account.api.web.dto.todo.GroupTodoAccountDto;
import com.account.api.web.dto.todo.TodoAccountDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Api(tags = {"1. Account"})
@RestController
@RequestMapping("/api/accounts") //컨트롤러 기본 URL
public class AccountController {
    //컨트롤러 구현 시작
    private final AccountService accountService;
    //jwt토큰을 decode하기 위함
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/test")
    public String testHello(){
        return "TEST";
    }

    @ApiOperation(value = "유저 데이터 추가", notes = "회원가입시 인증서버에서 인증이 되었을 경우 유저 정보를 추가한다.")
    @PostMapping()
    public String addAccount(@RequestBody AccountDto accountDto) {
       accountService.addAccount(accountDto.toEntity());
        return "success";
    }

    @ApiOperation(value = "내 정보 조회", notes = "JWT에서 유저정보를 취득하여 유저정보를 불러온다")
    @GetMapping("/info")
    public AccountDto getMyInfo(HttpServletRequest request) throws NoSuchElementException {
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String email = jwtTokenProvider.getAccountEmail(token);
        Account account = accountService.findByEmailAccount(email);
        return new AccountDto(account);
    }

    // account 목록
    @GetMapping()
    public List<AccountDto> getAccounts(){
        return accountService.getAccountList().stream().map(account->new AccountDto(account)).collect(Collectors.toList());
    }

    // account
    @GetMapping("/{accountId}")
    public AccountDto getAccount(@PathVariable String accountId) {
        return new AccountDto(accountService.getAccount(accountId));
    }

    // account 수정
    @PutMapping("/edit")
    public AccountDto modifyAccount(@RequestBody AccountModifyDto accountModifyDto){
        Account modifyAccount = accountService.getAccount(accountModifyDto.getAccountId());

        Account account = accountModifyDto.toEntity(modifyAccount);
        return new AccountDto(accountService.modifyAccount(account));
    }

    // account 삭제
    @DeleteMapping("/signout/{accountId}")
    public void deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
    }


    // Todo서비스에서 받아온 Todo데이터들 중에서 writer를 가지고 Account객체를 취득하여 넣어준다.
    // MSA로 나누다보니 해당작업을 프론트에서 처리하려고 했으나.. 서버쪽에서 하는편이 더 좋을것 같다고 생각하여 추가
    @PostMapping("/todos/mapping")
    public List<TodoAccountDto> todosAccountIdMapping(@RequestBody ArrayList<TodoAccountDto> todoAccountDtos){

        for(int i=0; i< todoAccountDtos.size(); i++) {
            String todoAccountId = todoAccountDtos.get(i).getWriter();
            todoAccountDtos.get(i).setAccountModel(accountService.getAccount(todoAccountId));

            for(int j=0; j<todoAccountDtos.get(i).getComments().size(); j++){
                String commentAccountId = todoAccountDtos.get(i).getComments().get(j).getWriter();
                todoAccountDtos.get(i).getComments().get(j).setAccountModel(accountService.getAccount(commentAccountId));

                for(int k=0; k< todoAccountDtos.get(i).getComments().get(j).getSubComments().size(); k++){
                    String subCommentAccountId = todoAccountDtos.get(i).getComments().get(j).getSubComments().get(k).getWriter();
                    todoAccountDtos.get(i).getComments().get(j).getSubComments().get(k).setAccountModel(accountService.getAccount(subCommentAccountId));
                }
            }
        }
        return todoAccountDtos;
    }

    // GroupTodo서비스에서 받아온 GroupTodo데이터들 중에서 writer를 가지고 Account객체를 취득하여 넣어준다.
    // MSA로 나누다보니 해당작업을 프론트에서 처리하려고 했으나.. 서버쪽에서 하는편이 더 좋을것 같다고 생각하여 추가
    @PostMapping("/grouptodos/mapping")
    public List<GroupTodoAccountDto> grouptodosAccountIdMapping(@RequestBody ArrayList<GroupTodoAccountDto> groupTodoAccountDtos){

        for(int i=0; i< groupTodoAccountDtos.size(); i++) {
            String todoAccountId = groupTodoAccountDtos.get(i).getWriter();
            groupTodoAccountDtos.get(i).setAccountModel(accountService.getAccount(todoAccountId));

            for(int j=0; j<groupTodoAccountDtos.get(i).getComments().size(); j++){
                String commentAccountId = groupTodoAccountDtos.get(i).getComments().get(j).getWriter();
                groupTodoAccountDtos.get(i).getComments().get(j).setAccountModel(accountService.getAccount(commentAccountId));

                for(int k=0; k< groupTodoAccountDtos.get(i).getComments().get(j).getSubComments().size(); k++){
                    String subCommentAccountId = groupTodoAccountDtos.get(i).getComments().get(j).getSubComments().get(k).getWriter();
                    groupTodoAccountDtos.get(i).getComments().get(j).getSubComments().get(k).setAccountModel(accountService.getAccount(subCommentAccountId));
                }
            }
        }
        return groupTodoAccountDtos;
    }

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    ErrorMessage runTimeError(RuntimeException e) throws NoSuchElementException {
        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        return error;
    }
}
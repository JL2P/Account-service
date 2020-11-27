package com.account.api.exception;

/**
 * 예외처리 커스텀
 * 해당 그룹이 존재하지 않을 때 발생 시키는 Exception
 * **/
public class AccountNotExistException extends RuntimeException {
    public AccountNotExistException(String msg, Throwable t) {
        super(msg, t);
    }
    public AccountNotExistException(String msg) {
        super(msg);
    }
    public AccountNotExistException() {
        super();
    }
}

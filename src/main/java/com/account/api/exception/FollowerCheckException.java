package com.account.api.exception;

public class FollowerCheckException extends RuntimeException {
    public FollowerCheckException(String msg, Throwable t) {
        super(msg, t);
    }
    public FollowerCheckException(String msg) {
        super(msg);
    }
    public FollowerCheckException() {
        super();
    }
}

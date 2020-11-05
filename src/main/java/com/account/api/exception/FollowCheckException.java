package com.account.api.exception;

public class FollowCheckException extends RuntimeException {
    public FollowCheckException(String msg, Throwable t) {
        super(msg, t);
    }
    public FollowCheckException(String msg) {
        super(msg);
    }
    public FollowCheckException() {
        super();
    }
}

package com.account.api.exception;

public class FollowingCheckException extends RuntimeException {
    public FollowingCheckException(String msg, Throwable t) {
        super(msg, t);
    }
    public FollowingCheckException(String msg) {
        super(msg);
    }
    public FollowingCheckException() {
        super();
    }
}

package com.nobiz.aics_u.exception;

public class NoRowsAffectedException extends RuntimeException{
    public NoRowsAffectedException(String msg) {
        super(msg);
    }
    public NoRowsAffectedException(String msg, Throwable cause) {
        super(msg, cause);
    }

}

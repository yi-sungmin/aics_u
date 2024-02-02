package com.nobiz.aics_u.exception;

public class UniqueNamingExistsException extends RuntimeException{
    public UniqueNamingExistsException(String msg) {
        super(msg);
    }
    public UniqueNamingExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

}

package com.goganesh.bookshop.exception;

public class NoJwtTokenException extends RuntimeException {

    public NoJwtTokenException(String message) {
        super(message);
    }
}

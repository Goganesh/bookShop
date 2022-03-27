package com.goganesh.bookshop.exception;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String message) {
        super(message);
    }
}

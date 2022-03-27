package com.goganesh.bookshop.exception;

public class NoSuchBookAction extends RuntimeException{
    public NoSuchBookAction(String message) {
        super(message);
    }
}

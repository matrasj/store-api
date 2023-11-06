package com.flexible.store.exception;

public class WebApiException extends RuntimeException{
    public WebApiException() {
        super();
    }

    public WebApiException(String message) {
        super(message);
    }
}

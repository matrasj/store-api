package com.flexible.store.exception.auth;

import com.flexible.store.exception.WebApiException;

public class InvalidCredentialsException extends WebApiException {
    public InvalidCredentialsException() {
        super("Invalid credentials");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}

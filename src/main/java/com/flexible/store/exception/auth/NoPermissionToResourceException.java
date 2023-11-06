package com.flexible.store.exception.auth;

import com.flexible.store.exception.WebApiException;

public class NoPermissionToResourceException extends WebApiException {
    public NoPermissionToResourceException() {
        super("No permission to resource");
    }

    public NoPermissionToResourceException(String message) {
        super(message);
    }
}

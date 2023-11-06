package com.flexible.store.exception.common;

import com.flexible.store.exception.WebApiException;

public class EntityNotFoundException extends WebApiException {
    public EntityNotFoundException() {
        super("Not found entity");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}

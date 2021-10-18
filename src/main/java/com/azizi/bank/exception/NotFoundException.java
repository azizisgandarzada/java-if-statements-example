package com.azizi.bank.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Exception cause) {
        super(message, cause);
    }

}

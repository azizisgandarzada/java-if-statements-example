package com.azizi.bank.exception.model;

import java.util.List;

public record Error(int status, String message, List<ValidationError> errors) {

    public Error(int status, String message) {
        this(status, message, null);
    }

}

package com.azizi.bank.exception;

import java.io.Serial;

public class AmountExceededMaxAmountException extends InvalidStateException {

    @Serial
    private static final long serialVersionUID = -1L;

    private static final String MESSAGE = "Payment amount exceeded merchant max amount";

    public AmountExceededMaxAmountException() {
        super(MESSAGE);
    }

}

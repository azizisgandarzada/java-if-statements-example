package com.azizi.bank.exception;

import java.io.Serial;

public class AmountBelowMinAmountException extends InvalidStateException {

    @Serial
    private static final long serialVersionUID = -1L;

    private static final String MESSAGE = "Payment amount was below minimum amount";

    public AmountBelowMinAmountException() {
        super(MESSAGE);
    }

}

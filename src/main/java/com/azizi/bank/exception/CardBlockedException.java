package com.azizi.bank.exception;

import java.io.Serial;

public class CardBlockedException extends InvalidStateException {

    @Serial
    private static final long serialVersionUID = -1L;

    private static final String MESSAGE = "CARD_BLOCKED";

    public CardBlockedException() {
        super(MESSAGE);
    }

}

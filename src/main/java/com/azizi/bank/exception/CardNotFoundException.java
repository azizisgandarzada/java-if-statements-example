package com.azizi.bank.exception;

import java.io.Serial;

public class CardNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = -1L;

    private static final String MESSAGE = "CARD_NOT_FOUND";

    public CardNotFoundException() {
        super(MESSAGE);
    }

}

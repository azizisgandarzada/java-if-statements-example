package com.azizi.bank.exception;

import java.io.Serial;

public class MerchantNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = -1L;

    private static final String MESSAGE = "MERCHANT_NOT_FOUND";

    public MerchantNotFoundException() {
        super(MESSAGE);
    }

}

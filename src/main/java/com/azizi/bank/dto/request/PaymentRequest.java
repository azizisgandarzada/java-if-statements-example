package com.azizi.bank.dto.request;

import java.math.BigDecimal;

public record PaymentRequest(Integer userId, Integer merchantId, Integer cardId, BigDecimal amount) {

}

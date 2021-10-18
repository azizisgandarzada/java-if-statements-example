package com.azizi.bank.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record PaymentRequest(@NotNull Integer userId, @NotNull Integer merchantId, @NotNull Integer cardId,
                             @NotNull @Positive BigDecimal amount) {

}

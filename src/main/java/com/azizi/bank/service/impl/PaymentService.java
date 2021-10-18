package com.azizi.bank.service.impl;

import com.azizi.bank.dto.request.PaymentRequest;

public sealed interface PaymentService permits PaymentServiceImpl {

    String pay(PaymentRequest paymentRequest);

}

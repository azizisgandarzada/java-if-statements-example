package com.azizi.bank.controller;

import com.azizi.bank.dto.request.PaymentRequest;
import com.azizi.bank.service.impl.PaymentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public String pay(@RequestBody @Valid PaymentRequest paymentRequest) {
        return paymentService.pay(paymentRequest);
    }

}

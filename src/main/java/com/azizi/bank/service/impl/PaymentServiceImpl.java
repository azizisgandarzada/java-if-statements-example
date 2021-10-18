package com.azizi.bank.service.impl;

import com.azizi.bank.dto.request.PaymentRequest;
import com.azizi.bank.entity.Card;
import com.azizi.bank.entity.Merchant;
import com.azizi.bank.enums.CardStatus;
import com.azizi.bank.repository.CardRepository;
import com.azizi.bank.repository.MerchantRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class PaymentServiceImpl implements PaymentService {

    private final MerchantRepository merchantRepository;
    private final CardRepository cardRepository;

    @Override
    public String pay(PaymentRequest paymentRequest) {
        Optional<Merchant> optionalMerchant = merchantRepository.findById(paymentRequest.merchantId());
        if (optionalMerchant.isEmpty()) {
            throw new RuntimeException("Merchant not found");
        }
        Optional<Card> optionalCard = cardRepository.findByIdAndUserId(paymentRequest.cardId(), paymentRequest.userId());
        if (optionalCard.isEmpty()) {
            throw new RuntimeException("Card not found");
        }
        if (optionalCard.get().getStatus() == CardStatus.BLOCKED) {
            throw new RuntimeException("Card is blocked");
        }
        if (paymentRequest.amount().compareTo(optionalMerchant.get().getMinAmount()) < 0) {
            throw new RuntimeException("Amount is below merchant minimum amount");
        }
        if (paymentRequest.amount().compareTo(optionalMerchant.get().getMaxAmount()) > 0) {
            throw new RuntimeException("Amount exceeded merchant maximum amount");
        }
        return "ok";
    }

}

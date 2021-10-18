package com.azizi.bank.service.impl;

import com.azizi.bank.dto.request.PaymentRequest;
import com.azizi.bank.entity.Card;
import com.azizi.bank.entity.Merchant;
import com.azizi.bank.enums.CardStatus;
import com.azizi.bank.exception.AmountBelowMinAmountException;
import com.azizi.bank.exception.AmountExceededMaxAmountException;
import com.azizi.bank.exception.CardBlockedException;
import com.azizi.bank.exception.CardNotFoundException;
import com.azizi.bank.exception.MerchantNotFoundException;
import com.azizi.bank.repository.CardRepository;
import com.azizi.bank.repository.MerchantRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class PaymentServiceImpl implements PaymentService {

    private final MerchantRepository merchantRepository;
    private final CardRepository cardRepository;

    @Override
    public String pay(PaymentRequest paymentRequest) {
        Merchant merchant = getOrElseThrowMerchantNotFound(paymentRequest.merchantId());
        Card card = getOrElseThrowCardNotFound(paymentRequest.cardId(), paymentRequest.userId());
        throwIfAmountBelowMerchantMinAmount(paymentRequest.amount(), merchant);
        throwIfAmountExceededMerchantMaxAmount(paymentRequest.amount(), merchant);
        throwIfCardBlocked(card);
        return "ok";
    }

    private Merchant getOrElseThrowMerchantNotFound(Integer merchantId) {
        return merchantRepository.findById(merchantId).orElseThrow(MerchantNotFoundException::new);
    }

    private Card getOrElseThrowCardNotFound(Integer cardId, Integer userId) {
        return cardRepository.findByIdAndUserId(cardId, userId).orElseThrow(CardNotFoundException::new);
    }

    private void throwIfCardBlocked(Card card) {
        if (card.getStatus() == CardStatus.BLOCKED) {
            throw new CardBlockedException();
        }
    }

    private void throwIfAmountExceededMerchantMaxAmount(BigDecimal amount, Merchant merchant) {
        if (amount.compareTo(merchant.getMaxAmount()) > 0) {
            throw new AmountExceededMaxAmountException();
        }
    }

    private void throwIfAmountBelowMerchantMinAmount(BigDecimal amount, Merchant merchant) {
        if (amount.compareTo(merchant.getMaxAmount()) < 0) {
            throw new AmountBelowMinAmountException();
        }
    }

}

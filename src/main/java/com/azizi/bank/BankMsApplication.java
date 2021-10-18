package com.azizi.bank;

import com.azizi.bank.entity.Card;
import com.azizi.bank.entity.Merchant;
import com.azizi.bank.enums.CardStatus;
import com.azizi.bank.repository.CardRepository;
import com.azizi.bank.repository.MerchantRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BankMsApplication implements CommandLineRunner {

    private final MerchantRepository merchantRepository;
    private final CardRepository cardRepository;

    public static void main(String[] args) {
        SpringApplication.run(BankMsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        merchantRepository.save(Merchant.builder()
                .name("Bakcell")
                .minAmount(new BigDecimal(1))
                .maxAmount(new BigDecimal(500))
                .build());
        cardRepository.save(Card.builder()
                .userId(1)
                .cardNumber("1111222233334444")
                .status(CardStatus.BLOCKED)
                .build());
    }

}

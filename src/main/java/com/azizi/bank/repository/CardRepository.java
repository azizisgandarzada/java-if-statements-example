package com.azizi.bank.repository;

import com.azizi.bank.entity.Card;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findByIdAndUserId(Integer id, Integer cardId);

}

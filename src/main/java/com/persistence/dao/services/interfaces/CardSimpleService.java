package com.persistence.dao.services.interfaces;

import com.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardSimpleService {
    List<Card> findAll();

    Optional<Card> id(long id) throws InterruptedException;

    Card addCard(Card oldCard);

    void deleteCardById( Long id);

    void listAllCard();

    List<Card> findCardByType(String type);

    List<Card> findCardByYearAndSent(Integer year, Boolean sent);

    int updateCardTypeByAuthorAndYear(String type, String name, Integer year);
}

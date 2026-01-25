package com.persistence.dao.services.interfaces;

import com.model.Card;

import java.util.List;

public interface CardSimpleService {
    List<Card> findAll();

    Card addCard(Card oldCard);

    void deleteCardById( Long id);

    void listAllCard();

    List<Card> findCardByType(String type);

    List<Card> findCardByYearAndSent(Integer year, Boolean sent);

    int updateCardTypeByAuthorAndYear(String type, String name, Integer year);
}

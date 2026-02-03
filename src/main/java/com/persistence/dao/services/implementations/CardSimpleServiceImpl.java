package com.persistence.dao.services.implementations;

import com.google.common.collect.Lists;
import com.model.Card;
import com.persistence.dao.repositories.CardRepository;
import com.persistence.dao.services.interfaces.CardSimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CardSimpleServiceImpl implements CardSimpleService {
    private CardRepository cardRepository;


    @Autowired
    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> findAll() {
        return Lists.newArrayList(cardRepository.findAll());
    }

    @Override
    @CachePut(value = "empl", condition = "#result != null", key = "#result.id")
    public Optional<Card> id(long id) throws InterruptedException {
        cardRepository.findAll();
        System.out.println("Getting employee from repo");
        return cardRepository.findById(id);
    }

    @Override
    public Card addCard(Card oldCard) {
        return cardRepository.save(oldCard);
    }

    @Override
    public void deleteCardById(@Param("id") Long id) {
    }


    @Override
    public void listAllCard() {
        cardRepository.findAll().forEach(System.out::println);
    }

    @Override
    public List<Card> findCardByType(String type) {
        return cardRepository.findCardByType(type);
    }

    @Override
    public List<Card> findCardByYearAndSent(Integer year, Boolean sent) {
        return cardRepository.findCardByYearAndSent(year, sent);
    }

    @Override
    public int updateCardTypeByAuthorAndYear(String type, String name, Integer year) {
        return cardRepository.updateCardTypeByAuthorAndYear(type, name, year);
    }

}

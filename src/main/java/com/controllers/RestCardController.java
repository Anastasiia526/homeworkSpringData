package com.controllers;

import com.model.Card;
import com.persistence.dao.services.interfaces.CardSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestCardController {

    private CardSimpleService cardSimpleService;

    @Autowired
    public void setCardSimpleService(CardSimpleService cardSimpleService) {
        this.cardSimpleService = cardSimpleService;
    }

    @GetMapping(value = "/card/{id}", produces = "application/json")
    public Card getCardInfo(@PathVariable("id") long id) throws InterruptedException {
return cardSimpleService.id(id).orElseThrow(()-> new IllegalArgumentException("No card with such id"));
    }

    @GetMapping(value = "/card/list", produces = "application/json")
    public List<Card> getCardInfo(){
        return cardSimpleService.findAll();
    }

    @PutMapping(value = "/card/add", consumes = "application/json")
    public Card addCardPut(@RequestBody Card card) {
        return cardSimpleService.addCard(card);
    }

}

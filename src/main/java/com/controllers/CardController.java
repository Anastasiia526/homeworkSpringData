package com.controllers;


import com.model.Card;
import com.persistence.dao.services.interfaces.CardSimpleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    private CardSimpleService cardSimpleService;

    public void setCardSimpleService(CardSimpleService cardSimpleService) {
        this.cardSimpleService = cardSimpleService;
    }


    @PostMapping(value = "/add")
    public String addCard(HttpServletRequest request) {
        Card card = new Card();
        card.setThema(request.getParameter("thema"));
        card.setType(request.getParameter("type"));
        card.setSent(Boolean.parseBoolean(request.getParameter("sent")));
        card.setCountry(request.getParameter("country"));
        card.setYear(Integer.parseInt(request.getParameter("year")));
        card.setAuthor(request.getParameter("author"));
        card.setValuable(request.getParameter("valuable"));
        cardSimpleService.addCard(card);
        return "redirect:/card/all";
    }

    @GetMapping(value = "/all")
    public ModelAndView listAllCard(ModelAndView modelAndView) {
        modelAndView.addObject("cards", cardSimpleService.findAll());
        modelAndView.setViewName("card");
        return modelAndView;
    }

    @GetMapping(value = "/remove/{id}")
    public String removeCard(@PathVariable("id") long id) {
        cardSimpleService.deleteCardById(id);
        return "redirect:/card/all";
    }

    @PostMapping(value = "/findCardByType")
    public ModelAndView findCardByType(@RequestParam("type") String type, ModelAndView modelAndView) {
        modelAndView.addObject("cards", cardSimpleService.findCardByType(type));
        modelAndView.setViewName("/search-results");
        return modelAndView;
    }

    @PostMapping(value = "/findCardByYearAndSent")
    public ModelAndView findCardByYearAndSent(@RequestParam("year") Integer year, @RequestParam("sent") Boolean sent, ModelAndView modelAndView) {
        modelAndView.addObject("cards", cardSimpleService.findCardByYearAndSent(year, sent));
        modelAndView.setViewName("/search-results");
        return modelAndView;
    }

    @PostMapping(value = "/update")
    public String updateCard(@RequestParam("type") String type, @RequestParam("author") String author, @RequestParam("year") Integer year) {
        cardSimpleService.updateCardTypeByAuthorAndYear(type, author, year);
        return "redirect:/card/all";
    }
}

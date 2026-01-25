package com.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "card")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String thema;
    private String type;
    private Boolean sent;
    private String country;
    private String author;
    private Integer year;
    private String valuable;


    @Override
    public String toString(){
        return "Thema " + thema + " type " + type + " isSent " + sent
                + " country " + country + " author " + author + " year " + year + " valuable " + valuable;

    }

    public Card() {
    }

    public Card(Long id, String thema, String type, Boolean sent, String country, String author, Integer year, String valuable) {
        this.id = id;
        this.thema = thema;
        this.type = type;
        this.sent = sent;
        this.country = country;
        this.author = author;
        this.year = year;
        this.valuable = valuable;
    }
}

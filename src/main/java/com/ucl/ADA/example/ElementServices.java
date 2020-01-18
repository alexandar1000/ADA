package com.ucl.ADA.example;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * This is a kinda-sorta wrapper around the Repository-type class, and exposes the functionality added by it, but with
 * additional functionality possibly added. It should handle most of the functionality written in the app.
 * */

@Service
public class ElementServices {

    @Autowired
    private ElementRepository elementRepository;

    private Faker faker = new Faker();

    List<Element> list() {
        return (List<Element>) elementRepository.findAll();
    }

    void saveElement(String name) {
        String country = faker.country().name();
        String yodaQuote = faker.yoda().quote();
        String pokemon = faker.pokemon().name();

        Element element = new Element(name, country, yodaQuote, pokemon);
        elementRepository.save(element);
    }


}

package com.ucl.ADA.example;
import javax.persistence.*;
import java.io.Serializable;

/*
* This is a model class.. All of the data, as in database will go here. Moreover, no functionality is to be added here.
* IMPORTANT: These classes need to have the default (empty) constructor.
* */

@Entity
public class Element implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String quote;

    @Column(nullable = false)
    private String pokemon;

    protected Element() {}

    public Element(String name, String country, String quote, String pokemon) {
        this.name = name;
        this.country = country;
        this.quote = quote;
        this.pokemon = pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        return "Hello " + name + "! As you are from " + country + ", we have a special Yoda quote for you: \""
                + quote + "\" Also, you are most similar to " + pokemon + "! :)";
    }
}

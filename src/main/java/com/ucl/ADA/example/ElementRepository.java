package com.ucl.ADA.example;
import org.springframework.data.repository.CrudRepository;

/*
 * This is a repository interface. Basically, it serves as a layer between the database and the app, with the knowledge
 * of the corresponding model. It automatically implements the functionality, and if you need something specific done,
 *  it is worth looking in the docs.
 * */


public interface ElementRepository extends CrudRepository<Element, Long> {}

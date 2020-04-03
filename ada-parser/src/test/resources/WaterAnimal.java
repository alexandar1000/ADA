package com.ucl.ADA.parser.test_resources;

import java.util.*;

public class WaterAnimal extends Animal implements IAnimal {

    private int id;
    private String name;
    private List<String> types;
    private Animal ani_var;

    enum Color {RED, BLUE;}

    void swim(int time) {
        Animal ani_x = new Animal();
        Animal ani_y = new Animal();
        int x = 5;
        int y = 6;
        List<String> list = new ArrayList<>();
        list.add("test");

    }


}

class Animal {

}

interface IAnimal {
}

enum Game {}

package com.ucl.ADA.parser.source_to_parse.package1;

import com.ucl.ADA.parser.source_to_parse.package2.Leg;

public class Dog extends Animal implements Entity {

    static long LENGTH = 12;

    public Leg getLeg(Fish fish, int n) {
        fish.swim();

        Cat cat = new Cat();
        cat.catchMouse();

        cat.eat(new Bulldog());

        return new Leg();
    }

    private static class Bulldog extends Animal {

        void bark() {
            int x = 5;
            Leg leg = new Dog().getLeg(new Fish(), x);
        }
    }

}

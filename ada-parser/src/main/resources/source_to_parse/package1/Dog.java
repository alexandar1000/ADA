package package1;

import package2.Leg;
import package2.Term;

public class Dog extends Animal implements Entity, Term {

    static long LENGTH = 12;

    public Dog() {

    }

    public Dog(int number, String str) {

    }

    public Leg getLeg(Fish fish, int x, int y, int z, String s) {
        fish.swim();

        Cat cat = new Cat();
        cat.catchMouse();


        cat.eat(new Bulldog());

        return new Leg();
    }

    class Bulldog extends Animal {

        void bark() {

            Leg leg = new Dog().getLeg(new Fish(), new Fish().getSpeed(), new Fish().SPEED, 777, "str");
        }

        class FatBullDog {
            void run() {
            }
        }
    }

    class Haski {
        void play() {
        }
    }

}

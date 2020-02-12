package package2;

import package1.Animal;
import package1.Dog;
import package1.Fish;

public class Bulldog extends Animal {

    void bark() {

        Leg leg = new Dog().getLeg(new Fish(), new Fish().getSpeed(), new Fish().SPEED, 777, "str");
    }

    class FatBullDog {
        void run() {
        }
    }
}
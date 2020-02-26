package declaration.constructor_declaration;

public class Bird {

    public Bird() {}

    protected Bird(int age, String name) {}

    Bird(int age, String name, BirdHead birdHead) {}

    Bird(BirdHead birdHead, int age, String name) {}

    Bird(BirdHead birdHead, Integer age, String name) {}

    // not constructor declaration
    public void Bird(String NOT_CONSTRUCTOR) {}
}

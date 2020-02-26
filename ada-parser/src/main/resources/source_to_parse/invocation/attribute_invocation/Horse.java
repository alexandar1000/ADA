package invocation.attribute_invocation;


public class Horse {

    public static int speed = Donkey.fast_speed + Donkey.slow_speed * 2;

    // self-invocation
    int mySpeed = Horse.speed;

    // TODO: add external attribution invocation

    // inside constructor
    public Horse() {
        HorseColor color = HorseColor.BLUE;

        System.out.println(new Donkey().name);
    }

    // inside method
    HorseColor horseChase() {
        new Donkey().chase(Donkey.fast_speed);

        return HorseColor.YELLOW;
    }

}

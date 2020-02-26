package declaration.method_declaration;

public class Fish implements WaterAnimal{

    private synchronized static Fisherman hookedBy(String location, WaterAnimal friend) {
        return new Fisherman();
    }

    void swim(int time, String name) {}

    void swim(String name, int time) {}

    @Override
    public void swim(int time) {}

    // not method declaration
    public Fish(String NOT_METHOD) {}


}

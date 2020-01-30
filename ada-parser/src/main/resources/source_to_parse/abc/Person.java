import java.util.ArrayList;

class Person {
    private String name;
    private int age;
    public ArrayList<Vehicle> cars;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.cars = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Vehicle> getCars() {
        return this.cars;
    }

    public void purchaseNewCar(String vrn, String type, String brand, String repairStatus) {
        Vehicle car = new Vehicle(vrn, type, brand, repairStatus);
        this.cars.add(car);
    }

    public String toString() {
        return "Person: " + this.getName() + "; Age: " + getAge();
    }
}
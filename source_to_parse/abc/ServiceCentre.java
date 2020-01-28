public class ServiceCentre {
    public static void main(String[] args) {
        Person person = new Person("Albert", 22);
        System.out.println("The person that came to the service centre is: " + person.toString());
        person.purchaseNewCar("12345", "electric", "TESLA", "broken");
        System.out.println("Car information: " + person.getCars().get(0).toString());
        serviceCar(person.getCars().get(0));
        System.out.println("Car has now been fixed.");
        System.out.println(person.getCars().get(0).toString());
    }

    public static void serviceCar(Vehicle car) {
        car.setRepairStatus("working");
    }
}

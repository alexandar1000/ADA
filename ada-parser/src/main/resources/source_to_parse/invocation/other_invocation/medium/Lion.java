package invocation.other_invocation.medium;

public class Lion {

    // inside constructor invocation parameters:
    Tiger tiger = new Tiger(
            new Teeth(),            // constructor invocation
            4,                  // int
            new Teeth().width,      // attribute invocation
            Teeth.type,             // (static) attribute invocation
            new Teeth().getType(),  // method invocation
            Teeth.staticGetType()); // (static) method invocation


    public Lion(Teeth teeth, int length) {

    }
}

//class Lional {
//
//    String name = "Messi";
//}
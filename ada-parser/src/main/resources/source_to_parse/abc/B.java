public class B {

    public B() {
       //default constructor
    }

    public void m1_B() {

        A a = new A();
        a.m1_A();
        a.m2_A();
        // calling a method of Class A from a method of Class B
    }

    public void m2_B() {
        C c = new C();
        c.m1_C();
        // calling a method of Class C from a method of Class B
    }

}

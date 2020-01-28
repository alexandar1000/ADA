
public class B {

	public B() {

	}

	public void m1_B() {

		A a = new A();
		a.m1_A();
		a.m2_A();
		System.out.println("Calling A");
	}

	public void m2_B() {
		C c = new C();
		c.m1_C();
	}

}

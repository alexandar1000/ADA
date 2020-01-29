public class C extends A implements I1, I2{
     // extends a class and implement two interfaces

// private class P inside class C
	private class P{

		private  int s1;

		public P() {

		}

		private  void m1_P(int p, int q) {
			int x=5;
			int y=6;
			B b = new B();
			b.m1_B();
		}
	}

	private static int s1;

	public C() {
          //default constructor
	}

	private static void m1_C(int p, int q) {
		int x=5;
		int y=6;
		this.m2_C(2,3);// calling own methods insider another method.
		B b = new B();
		b.m1_B();
		// calling a method of Class B from a method of Class C
	}
	private static void m2_C(int p, int q) {
		int x=5;
		int y=6;
		B b = new B();
		b.m1_B();
		// calling a method of Class B from a method of Class C
	}
}

// multiple classes insider a single source file.
class D{

	private static int s1;

	public D() {
		//default constructor
	}

	private  void m1_D(int p, int q) {
		int x=5;
		int y=6;
		B b = new B();
		b.m1_B();
		// calling a method of Class B from a method of Class D
	}
}

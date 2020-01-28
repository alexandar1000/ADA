
public class C extends A implements I1, I2{

	class P{

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

	}

	private static void m1_C(int p, int q) {
		int x=5;
		int y=6;
		this.m2_C(2,3);
		B b = new B();
		b.m1_B();
	}
	private static void m2_C(int p, int q) {
		int x=5;
		int y=6;
		B b = new B();
		b.m1_B();
	}

}


class D{

	private static int s1;

	public D() {

	}

	private  void m1_D(int p, int q) {
		int x=5;
		int y=6;
		B b = new B();
		b.m1_B();
	}
}

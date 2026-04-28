package practice;
//class A{
//	void funa() {
//		System.out.println("Class A function");
//	}
//}
//class B extends A{
//	void funb() {
//		System.out.println("Class b function");
//	}
//}

class A{
	void funa() {
		System.out.println("Class A function");
	}
}
class B extends A{
	void funb() {
		System.out.println("Class B function");
	}
	
}
class C extends A{
	void func() {
		System.out.println("Class C function");
	}
	
}


public class inheritance {
	public static  void main(String[]args) {
		A a = new A();
		B b= new B();
		C c = new C();
		
		a.funa();
		b.funa();b.funb();
		c.funa();c.func();
		
	}
}


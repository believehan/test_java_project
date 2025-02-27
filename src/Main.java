/*
 *  < 인터페이스를 통한 공통기능의 확장 >
 *  개별 객체에 대한 공통적인 기능에 대하여 그 세부적 내용만 차이가 있는 경우, 인터페이스에 그 공통
 *  기능을 명시하고 다수의 클래스에 각각의 세부 내용을 구현 재정의(Overriding)함으로써 개별
 *  객체 생성시 동일 인터페이스 타입 변수로 받음에 따라 공통 기능에 대한 세부적 다형성 확보가능
*/
interface Func{		// 공통 인터페이스
	void func1();
	void func2();
}

class ImpleClass1 implements Func {
	@Override
	public void func1() {
		System.out.println("구현1-1");
	}

	@Override
	public void func2() {
		System.out.println("구현1-2");		
	}	
}
class ImpleClass2 implements Func {
	@Override
	public void func1() {
		System.out.println("구현2-1");
	}
	
	@Override
	public void func2() {
		System.out.println("구현2-2");		
	}	
}

public class Main {
	public static void main(String[] args) {
		Func t1 = new ImpleClass1();	// 공통 인터페이스 타입변수에 개별 구현 객체를 대입함으로써
		Func t2 = new ImpleClass2();	// 객체별 공통 기능에 대한 세부적 다형성 확보

		t1.func1();
		t1.func2();
		t2.func1();
		t2.func2();

	}
}
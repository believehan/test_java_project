class Car {
	String name;
	String color;
	boolean gasoline;

	Car(String name, String color, boolean gasoline) {
		this.name = name;
		this.color = color;
		this.gasoline = gasoline;
	}

	void run() {
		if (gasoline) {
			System.out.println("부릉 부릉");
		} else {
			System.out.println("덜컹 덜컹");
		}
	}

	void stop() {
		System.out.println("끼이익");
	}
}

class Truck extends Car {
	int Ton;

	Truck(String name, String color, boolean gasoline, int ton) {
		super(name, color, gasoline);
		this.Ton = ton;
	}

	void run() {
		System.out.println("우당탕 쿵탕");
	}

	void convey() { // 서브에 추가 정의한 메서드
		System.out.println("짐을 실어 나른다");
	}

}

public class Main {
	public static void main(String[] args) {
		Car myTruck = new Car("봉고", "파랑", true);

		Truck anyTruck;
		anyTruck = (Truck) myTruck; // 슈퍼타입의 변수가 가리키고 있는 실제 대상이 슈퍼객체인 상황에서 서브 타입으로
									// 캐스팅 명시를 해봐야 당장 대입은 가능하나, 막상 실행을 해보면 예외가 발생
		anyTruck.run();				// 되어 제대로 실행되지 않음. 강제로 서브타입 변수가 슈퍼타입 변수를 대입 받긴
	}								// 했으나 실제 가리키는 대상이 슈퍼타입의 객체이다 보니 객체 지정 규직에 의해
}									// 실행시 예외 발생. 이처럼 실행시에 객체의 타입이 결정되는 경우 대입은 가능하나
									// 실행 시 문제가 야기 될 수 있으므로, 실제 실행중에 가리키고 있는 대상이 누구인지
									// 판별할 수 있는 기능 필요
class Car {
	String name;
	String color;

	public Car(String name, String color) {
		this.name = name;
		this.color = color;
	}
}
class OverridingCar {
	String name;
	String color;
	
	public OverridingCar(String name, String color) {
		this.name = name;
		this.color = color;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof OverridingCar) {
			
			OverridingCar tObj = (OverridingCar)arg0;
			return name.equals(tObj.name) && color.equals(tObj.color);
		}
		return false;
	}
	
	
}

public class Main {
	public static void main(String[] args) {
		OverridingCar sonata = new OverridingCar("소나타", "화이트");
		OverridingCar sonata2 = new OverridingCar("소나타", "화이트");
		OverridingCar sonata3 = new OverridingCar("스포티지", "화이트");
		Car sonata4 = new Car("소나타", "화이트");

		System.out.printf("sonata와 sonsta2는 %s\n", sonata.equals(sonata2) ? "같다" : "다르다");
		System.out.printf("sonata2와 sonsta3는 %s\n", sonata2.equals(sonata3) ? "같다" : "다르다");
		System.out.printf("sonata와 sonsta4는 %s\n", sonata.equals(sonata4) ? "같다" : "다르다");
		
		// (41행 주석) sonata4는 22행의 instanceof연산자에 의해 OverridngCar타입의 객체가 아님으로 평가되므로 바로 false리턴.
	}
}
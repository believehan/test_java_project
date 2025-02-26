// 탱크정보만 출력, 엑세스 지정자, 생성자 사용

class Tank {
	protected String name;
	protected int cannon;
	protected boolean missaile;
	protected boolean nuclear;
	static int takeNum;

	public Tank(String name, int cannon) {
		this.name = name;
		this.cannon = cannon;
	}

	public Tank(String name, int cannon, boolean missaile) {
		this(name, cannon);
		this.missaile = missaile;
	}

	public Tank(String name, int cannon, boolean missaile, boolean nuclear) {
		this(name, cannon, missaile);
		this.nuclear = nuclear;
	}

	public static void infoTank(Tank tank) {
		System.out.printf("\n< %s 탱크 >", tank.name);
		System.out.printf("\n대포 : %d단계", tank.cannon);
		if (tank.missaile) {
			System.out.printf("\n미사일 %s장착", tank.missaile ? "" : "미");
		}
		if (tank.nuclear) {
			System.out.printf("\n핵미사일 %s장착", tank.nuclear ? "" : "미");
		}
		takeNum++;
		System.out.printf("\n생산번호 : %d\n", takeNum);
	}

	public static void autoInfoTank(Tank... tank) {
		System.out.println("=========================");
		for (Tank auto : tank) {
			infoTank(auto);
		}

	}

}

public class Main {
	public static void main(String[] args) {
		Tank k1 = new Tank("K1", 1); // 객체
		Tank k2 = new Tank("K2", 2, true);
		Tank k2_1 = new Tank("K2_1", 2, true);
		Tank k3 = new Tank("K3", 3, true, true);

		Tank.infoTank(k1); // 출력
		Tank.infoTank(k2);
		Tank.infoTank(k2_1);
		Tank.infoTank(k3);

		Tank.autoInfoTank(k1, k2); // 전달한 내용만 출력
		Tank.autoInfoTank(k1, k2, k3);
	}
}
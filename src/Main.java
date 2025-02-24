class Tank {
	String name;
	int cannon;
	boolean missile;
	boolean reset = false;

	void setting(String name, int cannon, boolean missile) {
		this.name = name;
		this.cannon = cannon;
		this.missile = missile;
		reset = true;
	}

	void tank_type() {
		if (reset) {
			System.out.printf("탱크명 : %s, 포종류 : %d단계, ", name, cannon);
			System.out.println(missile ? "미사일 장착" : "미사일 미장착");
//			System.out.printf("미사일 %s장착\n", missile ? "":"미");// 선생님 답
		} else {
			System.out.println("탱크 제작 실패");
		}
	}

	void attack() {
		System.out.println("\n");
		System.out.printf("< %s 공격>", name);
		
		String casound="";
		switch (cannon) {
		case 1:
			casound = "펑";
			break;
		case 2:
			casound = "펑펑";
			break;

		case 3:
			casound = "콰광";
			break;
		}
		
		System.out.printf("대포발사 : %s", casound);
		if (missile) System.out.println("\n미사일 발사 : 초전박살");
	}

}

public class Main {
	public static void main(String[] args) {
		Tank k1 = new Tank();
		Tank k2 = new Tank();
		Tank k3 = new Tank();

		k1.setting("K1", 1, false);
		k2.setting("K2", 2, false);
		k3.setting("K3", 3, true);

		k1.tank_type();
		k2.tank_type();
		k3.tank_type();

		k1.attack();
		k2.attack();
		k3.attack();
	}
}
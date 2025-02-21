class Main {
	public static void main(String[] args) {
		int age = 25;
		boolean man = true;

		if (age >= 19) {
			if (man) {
				System.out.println("성인 남성 : 25000원");
			} else {
				System.out.println("성인 여성 : 21000원");
			}
		}else {
			System.out.println("청소년 : 15000원");
		}
	}
}
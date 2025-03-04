public class Main {
	public static void main(String[] args) {
		long startTime, endTime;

		startTime = System.currentTimeMillis();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			System.out.println("i=" + i + "\n");
		}
		endTime = System.currentTimeMillis();

		System.out.println("경과시간 : " + (endTime - startTime) / 1000.0 + "초");
	}
}
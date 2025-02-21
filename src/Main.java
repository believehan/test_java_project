import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		int star;
		Scanner num = new Scanner(System.in);

		do {
			System.out.print("출력할 줄 수 입력 (1보다 큰 자연수만 입력): ");
			star = num.nextInt();
		} while (star <= 0);

		for (int i = 1; i <= star; i++) { // 줄수

			for (int j = 0; j < star * 2 - i; j++) { // 열
				if (j < i - 1) {
					System.out.print(" ");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}

		num.close();
	}
}
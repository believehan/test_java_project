import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int num, tot = 0;
		Scanner sc = new Scanner(System.in);

		System.out.print("입력받을 인원 수 입력 : ");
		num = sc.nextInt();
		System.out.println("성명, 학번, 점수를 입력하시오!!");
		Object[][] ar = new Object[num][3];

		for (int i = 0; i < num; i++) {
			System.out.print("성명 : ");
			ar[i][0] = sc.next();
			System.out.print("학번 : ");
			ar[i][1] = sc.nextInt();
			System.out.print("점수 : ");
			ar[i][2] = sc.nextInt();
			System.out.println();
			tot += ar[i][2];
		}
		System.out.println("성명       학번       점수");
		for (int i = 0; i < num; i++) {
			System.out.printf("%s     %04d      %3d\n", 
					(String)ar[i][0], (Integer)ar[i][1], (Integer)ar[i][2]);
		}
		
		System.out.printf("총점 : %d,  평균 : %d", tot, tot/num);
		sc.close();
	}
}
// 2번 2.1 for문 10개 쓰지 말고 출력/ 2.2 다차원 배열 사용
package com.hme.main;

public class Main {
	public static void main(String[] args) {
		int[] ar1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] ar2 = { 2, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] ar3 = { 3, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] ar4 = { 4, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] ar5 = { 5, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] ar6 = { 6, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] ar7 = { 7, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] ar8 = { 8, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] ar9 = { 9, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] ar10 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

		for (int i = 0; i < 10; i++) {
			System.out.printf("ar%d 배열 : ", i+1);
			
			for (int j = 0, v = 0; j < 10; j++) {
				switch (i) {
				case 0:
					v = ar1[j];
					break;
				case 1:
					v = ar2[j];
					break;
				case 2:
					v = ar3[j];
					break;
				case 3:
					v = ar4[j];
					break;
				case 4:
					v = ar5[j];
					break;
				case 5:
					v = ar6[j];
					break;
				case 6:
					v = ar7[j];
					break;
				case 7:
					v = ar8[j];
					break;
				case 8:
					v = ar9[j];
					break;
				case 9:
					v = ar10[j];
					break;
				}
				System.out.print(v + " ");
			}
			System.out.println();

			
		}
	}
}
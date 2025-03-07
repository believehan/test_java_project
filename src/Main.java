import java.util.Scanner;

public class Main {
	public class Custom extends Exception {

	}

	static final int correctpw = 1234; // 저장되어있는 비밀번호로 가정
	static int pw = 0; // 입력 비밀번호
	static int fMth = 0; // inputPw메서드에 대한 최초 호출 명시 스위치.

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		inputPw(sc); // 비밀번호 입력을 위한 일회성 메서드. 메서드 재호출 시 스캐너 객체를 닫어 호출 불가 상태이어야 함으로 설계.
						// 즉, 비밀번호가 일치하던 불일치하던 3회 입력 후에는 스태너 객체를 close하여 15행의 메서드 호출이
						// 불가하도록 설계.

		inputPw(sc); // 비밀번호 3회 불일치 시 재입력 시도 가정.
		inputPw(sc); // 비밀번호 3회 불일치 시 재입력 시도 가정.
		inputPw(sc); // 비밀번호 3회 불일치 시 재입력 시도 가정.
		inputPw(sc); // 비밀번호 3회 불일치 시 재입력 시도 가정.
	}

	static void inputPw(Scanner sc) {
		try {
			for (int i = 0; i < 3; i++) {
				try {
					if (fMth == 0) { // inputPw메서드 최초 호출 시에만
						System.out.print("패스워드 4자리 숫자 입력 : "); // 23행의 문자열이 출력되도록 설정.
					}

					pw = sc.nextInt();
					if (pw == correctpw) {
						System.out.println("패스워드 일치 로그인 성공");

						return;
					}
				} catch (Exception e) {
					try {
						sc.nextLine(); // 26행에서 문자 입력 시 예외 발생으로 인한 입력버퍼 개행문자

					} catch (Exception e1) {
						if (pw != correctpw) {
							System.out.println("은행가라");
						}
						return;
					}
					System.out.println(i + 1 + "회 입력 오류"); // 제거. 또한 비밀번호 일치 여부를 떠나 15행의 메서드 재
					System.out.println("숫자만 입력 가능합니다."); // 호출 시 이미 소멸된 스캐너 객체를 26행에서 불법적 접근을
					continue; // 하게됨으로써 예외가 발생되어 catch블럭에 의해 처리가 되나
								// 당행에서 다시 예외가 발생되어 강제종료.
				}

				System.out.println(i + 1 + "회 입력 오류");
			}
			System.out.println("비밀번호 3회 오류 접속 불가");
		} finally {
			
			fMth = 1; // inputPw메서드에 대한 최초 호출이 아님 명시 스위치
			sc.close(); // 비밀번호 3회 입력 오류 시 스캐너 객체 해제
		}

	}
}
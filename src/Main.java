import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ClientManager {
	private Scanner sc;
	private HashMap<Integer, ClientInfo> client;

	private class ClientInfo {
		private static int clientCnt = 0;
		private int clientNum;
		private String clientName;
		private int clientAge;
		private String clientContact;

		public ClientInfo(String clientName, int clientAge, String clientContact) {
			this.clientName = clientName;
			this.clientAge = clientAge;
			this.clientContact = clientContact;
			clientNum = ++clientCnt;
		}
	}

	public ClientManager(int i) {
		sc = new Scanner(System.in);
		client = new HashMap<Integer, ClientInfo>(i);
	}

	public void viewClientInfo() {
		System.out.println("< 전체 등록 고객 확인 시스템 >\n");
		System.out.printf("----등록된 전체 고객 수: %d -----\n\n", client.size());

		for (Map.Entry<Integer, ClientInfo> entry : client.entrySet()) {
			ClientInfo client = entry.getValue();

			System.out.printf("고객번호 : %d\n", entry.getKey());
			System.out.printf("고객명 : %s\n", client.clientName);
			System.out.printf("나이 : %d\n", client.clientAge);
			System.out.printf("연락처 : %s\n\n", client.clientContact);
		}
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>전체 등록고객 확인완료!!\n\n\n");
	}

	public void addClient() {
		System.out.println("< 고객등록 시스템 >\n");

		String name, contact;
		int age;
		for (;;) {
			System.out.print("고객명 : ");
			name = sc.nextLine();
			if (name.isEmpty()) {
				break;
			} else {
				System.out.print("나이 : ");
				age = sc.nextInt();
				System.out.print("연락처 : ");
				contact = sc.next();
				System.out.println();
				ClientInfo putinfo = new ClientInfo(name, age, contact);
				client.put(putinfo.clientNum, putinfo);

				sc.nextLine(); // 남아있는 개행문자를 초기화
			}
		}
	}

	public void searchClient() {
//		int num;
//		System.out.println("< 등록고객 검색 시스템 >\n");
//
//		for (;;) {
//			System.out.println("\n==========================================\n");
//			System.out.print("찾을 고객번호 : ");
//			num = sc.nextInt();
//			if (num == -1) {
//				break;
//			}
//			if (client.containsKey(num)) {
//				if (client.get(num).clientNum == num) {
//					System.out.println("----검색된 고객정보----");
//					System.out.printf("고객명 : %s\n", client.get(num).clientName);
//					System.out.printf("나이 : %d\n", client.get(num).clientAge);
//					System.out.printf("연락처 : %s\n", client.get(num).clientContact);
//				}
//			} else {
//				System.out.println("해당 고객번호는 존재하지 않습니다.\n");
//			}
//		}
//		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>검색완료!\n\n");
	}

	public void removeClient() {
		int num;
		System.out.println("< 고객등록 해지 시스템 >\n");
		for (;;) {
			System.out.print("삭제 할 고객번호:");
			num = sc.nextInt();
			if (num == -1) {
				break;
			}
			if (client.containsKey(num)) {
				no :
				for (;;) {
					if (client.get(num).clientNum == num) {
						String yesno;
						System.out.printf("정말 고객번호 %d번 %s님을 등록 해지하시겠습니까?(y/n) : ", num, client.get(num).clientName);
						yesno = sc.next();
						switch (yesno) {
						case "y": {
							System.out.printf("%d번, %s님을 등록 해지하였습니다.\n", num, client.get(num).clientName);
							client.remove(num);
						}
						case "n": {
							break no;
						}
						default:
							System.out.println("입력이 잘못 되었습니다.\n");
						}
					}
				}
			} else {
				System.out.println("해당 고객번호는 존재하지 않습니다.\n");
			}
		}
		System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>> 삭제 완료!\n\n");
	}

}

public class Main {
	public static void main(String[] args) {
		ClientManager clientManager = new ClientManager(10);

		clientManager.viewClientInfo(); // 전체 등록고객 확인 메서드.

		clientManager.addClient(); // 고객등록 메서드.
		clientManager.searchClient(); // 고객검색 메서드.
		clientManager.removeClient(); // 고객등록 해지 메서드.

		clientManager.viewClientInfo(); // 전체 등록고객 확인 메서드
	}
}
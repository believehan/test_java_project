package com.psy7758.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.psy7758.dao.imp.MariaDao;
import com.psy7758.dao.imp.MysqlDao;
import com.psy7758.dto.ClientInfo;
import com.psy7758.service.imp.AdminService;

public class AdminController {
	int pageNum = 0;
	int breaknum = 1, num = 1;
	private Scanner scanner = new Scanner(System.in);
	private AdminService service;

	public AdminController() {
		// DB접속 메서드
		accessUser();
		while (true) {
			// 출력 매서드
			switch (breaknum) {
			case 1, 2: {
				printClientInfoList();
			}
			}
			// 페이지네이션 메서드

			pagination();

			if (breaknum == 3) {
				break;
			}
		}

		// 닫기 메서드
		closeInfoManagementUI();

		System.out.println("\n\n==== < 프로그램 종료 > ====");
	}

	/*
	 * =============================================================================
	 * ========================================
	 */
	// 출력 관련 메서드.

	private void printLineSpace() {
		System.out.println("\n\n");
	}

	private void printLine(char line) {
		for (int i = 0; i < 140; i++) {
			System.out.print(line);
		}
		System.out.println();
	}

	private void printLine1() {
		printLine('-');
	}

	private void printLine2() {
		printLine('=');
	}

	/*
	 * =============================================================================
	 * ========================================
	 */
	// DB 접속 메서드.

	private void accessUser() {
		String dbmsName;
		String userName;
		String psw;

		/*
		 * 정상적으로 접속이된 경우에는 이후 새로운 접속이 발생해도 최초 HikariDataSource 객체를 그대로 유지해야 하지만, 사용자명이나
		 * 패스워드가 달라 예외가 발생된 경우에는 기존의 접속 정보를 그대로 유지하면, 이후 정상적이 접속 정보를 입력해도 이전의 잘못된 접속 정보를
		 * 가지고 있는 HikariDataSource 객체가 그대로 사용되어 예외가 발생. 따라서 아래와같이 accessOk 를 이용 정상 접속인
		 * 경우에는 true 값을 유지하여 기존 접속을 그대로 유지하게 하되, 접속 정보가 틀려 예외가 발생된 경우에는 accessOk 를 false
		 * 로 변경함으로써, 입력받은 접속 정보를 토대로 새로운 HikariDataSource 객체를 생성하도록 구현.
		 */
		for (boolean accessOk = true;;) {
			printLine2();
			System.out.printf("사용하려는 DBMS명 입력( mysql : mysql, 마리아 : maria ) : ");
			dbmsName = scanner.next();
			System.out.printf("해당 DBMS 사용자명 : ");
			userName = scanner.next();
			System.out.printf("해당 DBMS 패스워드 : ");
			psw = scanner.next();
			printLine2();

			try {
				switch (dbmsName) {
				case "mysql":
					service = new AdminService(new MysqlDao(userName, psw, accessOk));
					break; // AdminService생성자((Mysql생성자))
				case "maria":
					service = new AdminService(new MariaDao(userName, psw, accessOk));
					break;
				default:
					System.err.println("구성되어 있는 DBMS 와 일치하지 않습니다.");
					continue;
				}
				/*
				 * HikariCP 잘못되 접속 정보를 전달하는 경우, ExceptionInInitializerError 가 발생되는데, Error 이므로
				 * Exception 으로 예외를 잡지 못하므로 Throwable 로 잡아야함에 유의.
				 * 
				 * ※ ExceptionInInitializerError : 정적 초기화 블록이나 클래스 초기화 중 발생되는 에러.
				 */
			} catch (Throwable e) {
				System.err.println("\n사용자명 또는 패스워드가 잘못 입력되었습니다.");
				accessOk = false;
				continue;
			} finally { // finally 블럭은 예외발생 여부와 관련없이 무조건 실행되므로 continue 적용 불가 유의.
				printLineSpace();
			}

			break;
		}

		printLine1();
		System.out.printf("%s 님 성공적으로 접속되었습니다.\n", userName);
		printLine1();
		printLineSpace();
	}

	/*
	 * =============================================================================
	 * ========================================
	 */
	// 고객 정보 조회 메서드.

	private void printClientInfoList() {
		ArrayList<ClientInfo> clientInfo = service.getClients(pageNum);

		printLine2();
		System.out.println("< 고객 정보 관리 >");
		System.out.printf("총 회원수 : %d\n", service.getListCnt());
		// Dao에서 상속받은 DBMS에서 SQL문으로 계산, Service로 정보 넘김, AdminService에서 정보 반환
		printLine2();

		System.out.printf("%8s  |  %17s  |  %17s  |  %17s  |  %17s  |  %20s  |  %10s\n", "REGNUM", "ID", "PASSWORD",
				"USERNAME", "PHONENUM", "BIRTHDATE", "TOTPOINT");
		printLine1();

		for (int i = 0; i < clientInfo.size(); i++) {
			System.out.printf("%8d  |  %17s  |  %17s  |  %17s  |  %17s  |  %20s  |  %10d\n",
					clientInfo.get(i).getRegNum(), clientInfo.get(i).getId(), clientInfo.get(i).getPwd(),
					clientInfo.get(i).getName(), clientInfo.get(i).getPhoneNum(), clientInfo.get(i).getBirthdate(),
					clientInfo.get(i).getTotPoint());
		}
		System.out.printf("%50d page / %d page\n", num, (int) Math.ceil(service.getListCnt() / 5) + 1);

	}

	/*
	 * =============================================================================
	 * ========================================
	 */
	// 페이지 넘기기 메서드
	private void pagination() {
		printLine1();
		printLine2();
		System.out.print("1 - 이전 페이지 / 2 - 다음 페이지 / 3 - 종료 : ");
		try {
			breaknum = scanner.nextInt();

		} catch (Exception e) {
			System.err.println("\n\n\n!! 고객 데이터가 존재하지 않습니다....");
		}

		switch (breaknum) {
		case 1: {
			num -= 1;
			pageNum -= 5;
			if (pageNum <= 0) {
				num = 1;
				pageNum = 0;
			}
			break;
		}
		case 2: {
			num += 1;
			pageNum += 5;
			if (num > service.getListCnt() / 5 + 1) {
				num -= 1;
				pageNum -= 5;
				break;
			}
			break;
		}
		case 3: {
			break;
		}
		default: {
			System.err.println("1,2,3 만 입력하세요");
			break;
		}
		}

	}
	/*
	 * =============================================================================
	 * ========================================
	 */
	// DB 접속 해제 메서드.

	private void closeInfoManagementUI() {
		service.close(); // 서비스에서 close메소드 가져오기
		scanner.close();
	}
}

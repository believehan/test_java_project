package com.psy7758.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.psy7758.dto.ClientInfo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public abstract class CommonModule implements Dao {
	/*
	 * DataSourceHolder 가 정적이너 클래스이고, 내부 createDataSource 메서드에서 생성자를 통해 전달된 접속(url,
	 * userName, psw)에 접근하여 정보를 초기화 하기 위해서 아래와같이 정적 필드로 선언해야함에 주의.
	 */
	private static String url;
	private static String userName;
	private static String psw;

	/*
	 * HikariDataSource 를 담는 정적 필드를 아래와같이 선언하여 DataSourceHolder 에서 생성한 싱글턴 형식의
	 * HikariDataSource 를 CommonModule 객체 생성 시점 최초에만 생성함으로써, CommonModule 의 개별 메서드에서
	 * 최초 메서드 호출 시점에 생성하는 기존 방식을 탈피하여, DAO 객체 생성시 자연스럽게 HikariDataSource 객체가 생성되도록
	 * 구현. 이에 따라 CommonModule 의 각 메서드에서는 당 비정적 필드를 통해 직접 HikariDataSource 에 접근이
	 * 가능해지고, 컨트롤러에서는 최초 DAO 객체 생성시 접속 정보가 틀리면 자연스러운 예외처리가 가능. 또한 최초 정상 접근된
	 * HikariDataSource 객체로 고정을 시키되, 비정상 접근시 재할당을 위해 final 미지정.
	 */
	private static HikariDataSource dataSource;

	public CommonModule(String url, String user_name, String psw, boolean accessOk) {
		CommonModule.url = url;
		CommonModule.userName = user_name;
		CommonModule.psw = psw;

		/*
		 * HikariDataSource 를 명시적 초기화를 하면, 해당 시점에서 생성자를 통해 전달된 필드 초기화가 되지 않은 상태에서
		 * HikariDataSource 객체가 생성되므로, 이래와같이 필드 초기화후 참조를 얻도록 해야함에 주의.
		 */
		if (accessOk) {
			dataSource = DataSourceHolder.dataSource;
		} else { // 필드 초기화 코드 (accessOk= false)
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(CommonModule.url);
			config.setUsername(CommonModule.userName);
			config.setPassword(CommonModule.psw);

			dataSource = new HikariDataSource(config);
			/*
			 * 상기 코드보다 아래 코드와 같이 DataSourceHolder 에 setDataSource 와 같은 public 메서드를 활용해 내부
			 * createDataSource 메서드를 재호출해 사용하는 것이 간단하지만, setDataSource 호출을 해도 실제
			 * createDataSource 메서드가 실행 되지는 않음. 즉, 내부적으로 호출 자체가 무효화 처리.
			 */
//         dataSource = DataSourceHolder.setDataSource();
		}
	}

	public static class DataSourceHolder {
		/* 최초 생성된 객체를 유지하기 위해 final 지정. */
		private static final HikariDataSource dataSource = createDataSource();

		private static HikariDataSource createDataSource() {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(url);
			config.setUsername(userName);
			config.setPassword(psw);

			return new HikariDataSource(config);
		}

//        public static HikariDataSource setDataSource() {
//           return createDataSource();
//      }
	}

	public ArrayList<ClientInfo> getClientsDb(String querySql) throws SQLException {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(querySql)) {

			ArrayList<ClientInfo> clients = new ArrayList<ClientInfo>();

			for (Date birthdate; resultSet.next();) {
				birthdate = resultSet.getDate("birthdate");

				clients.add(new ClientInfo(resultSet.getInt("regNum"), resultSet.getString("id"),
						resultSet.getString("pwd"), resultSet.getString("name"), resultSet.getString("phoneNum"),
						birthdate == null ? null : birthdate.toLocalDate(), resultSet.getInt("totPoint")));
			}

			return clients;
		}
	}

	public int getListCntDb(String querySql) throws SQLException {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(querySql);) {

			/*
			 * 데이터를 읽을 때는 당 예시에서와 같이 전체 레코드를 순회할 필요가 없다고 하더라도 반드시 커서 포인터가 첫 번째 행에 진입이 된
			 * 상태여야만 정상적 결과 집합을 수신 가능. 따라서 결과집합에 대한 next 메서드 호출은 필수.
			 */
			resultSet.next();

			int cnt = resultSet.getInt("CNT");

			return cnt;
		}
	}

	public void close() throws SQLException {
		dataSource.close(); // 히카리cp종료
	}
}
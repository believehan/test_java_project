package com.psy7758.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import com.psy7758.dto.Client;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public abstract class CommonModule implements Dao {
	private static String url;
	private static String user_name;
	private static String psw;

	public CommonModule(String url, String user_name, String psw) {
		CommonModule.url = url; // this.url = url이렇게 써도 됨
		CommonModule.user_name = user_name;
		CommonModule.psw = psw;
	}

	private static class DataSourceHolder {
		private static final HikariDataSource dataSource = createDataSource();

		private static HikariDataSource createDataSource() {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(url);
			config.setUsername(user_name);
			config.setPassword(psw);

			return new HikariDataSource(config);
		}
	}

	public ArrayList<Client> getClientData(String selectSql, String searchWord) throws SQLException {
		try (
				// 중첩 클래스에서 외부 주 클래스에서는 이너 클래스의 필드가 private 으로 설정되어 있어도 직접 접근 가능.
				Connection connection = DataSourceHolder.dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
			preparedStatement.setString(1, "%" + searchWord + "%");

			ArrayList<Client> clients = new ArrayList<Client>();
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Client client = new Client();

					client.setNum(resultSet.getInt("num"));
					client.setId(resultSet.getString("id"));
					client.setPwd(resultSet.getString("pwd"));
					client.setName(resultSet.getString("name"));
					client.setPhoneNum(resultSet.getString("phoneNum"));
					client.setBirthDate(Optional.ofNullable(resultSet.getDate("birthDate"))
							.map(java.sql.Date::toLocalDate).orElse(null));
					client.setTotPoint(resultSet.getInt("totPoint"));
					client.setRegDate(Optional.ofNullable(resultSet.getTimestamp("regDate"))
							.map(java.sql.Timestamp::toLocalDateTime).orElse(null));
					client.setPub(resultSet.getBoolean("pub"));

					clients.add(client);
				}
			}

			return clients;
		}
	}

	public int setPub(String updateSql, String id) throws SQLException {
		try (Scanner sc = new Scanner(System.in)) {
			try (Connection connection = DataSourceHolder.dataSource.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
				preparedStatement.setQueryTimeout(2);
				connection.setAutoCommit(false);

				preparedStatement.setString(1, id);
				int row = preparedStatement.executeUpdate();
				connection.commit();
				System.out.println("정상 업데이트!!");

				return row;
			} catch (SQLTimeoutException e) {
				System.out.println("접속 대기중...............");
				System.out.print("재시도('y') : ");

				if (!sc.next().equalsIgnoreCase("y")) {
					System.out.println("접속을 종료합니다!!");
					return 0;
				}

				System.out.println("재접속중...............");

				return setPub(updateSql, id);
			}
		}
	}
}
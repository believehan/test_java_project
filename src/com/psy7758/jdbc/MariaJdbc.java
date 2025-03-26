package com.psy7758.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaJdbc {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
// 구조이해 연습피일 
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mariadb://localhost:3307/maria_db";

		Connection conn = DriverManager.getConnection(url, "mideum2", "1234");
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM client ORDER BY regDate;");

		if (resultSet.next()) {
			System.out.printf("ID : %s\n", resultSet.getString("id"));
			System.out.printf("BIRTHEATE : %s\n", resultSet.getString("birthdate"));
			System.out.printf("TOTPOINT : %d\n", resultSet.getInt("totpoint"));
		}

		resultSet.close();
		statement.close();
		conn.close();
	}
}

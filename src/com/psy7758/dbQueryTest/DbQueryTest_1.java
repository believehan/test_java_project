package com.psy7758.dbQueryTest;

import java.sql.SQLException;

import com.psy7758.service.Service;
import com.psy7758.service.imp.AdminService;

public class DbQueryTest_1 {
	public static void main(String[] args) throws SQLException, InterruptedException {
		Service service = new AdminService();

		System.out.println(service.getClient() + "\n");
	}
}
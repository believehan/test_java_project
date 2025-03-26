package com.psy7758.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.psy7758.dao.CommonModule;
import com.psy7758.dto.Client;

public class MysqlDao extends CommonModule {
	private static final String URL = "jdbc:mysql://localhost:3306/test_db";
	private static final String USER_NAME = "mideum";
	private static final String PSW = "1234";

	public MysqlDao() {
		super(URL, USER_NAME, PSW);
	}

	@Override
	public ArrayList<Client> getClient(String searchField, String searchWord, boolean pub) throws SQLException {
		String selectSql = String.format("SELECT @rowNum := @rowNum + 1 num, client.* "
				+ "FROM client, ( SELECT @rowNUm := 0 ) rn " + "WHERE %s LIKE ? %s " + "ORDER BY regDate", searchField,
				pub ? "" : "AND pub = 1");

		return getClientData(selectSql, searchWord); // 다 전달할 필요 없음
	}

	@Override
	public int setClientPubTrue(String id) throws SQLException {
		String updateSql = String.format("UPDATE client set pub = 1 WHERE id = ?");

		return setPub(updateSql, id);
	}
}
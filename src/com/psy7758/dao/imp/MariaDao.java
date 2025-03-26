package com.psy7758.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.psy7758.dao.CommonModule;
import com.psy7758.dto.Client;

public class MariaDao extends CommonModule {
	private static final String URL = "jdbc:mariadb://localhost:3307/maria_db";
	private static final String USER_NAME = "mideum2";
	private static final String PSW = "1234";

	public MariaDao() {
		super(URL, USER_NAME, PSW);
	}

	@Override
	public ArrayList<Client> getClient(String searchField, String searchWord, boolean pub) throws SQLException {
		String selectSql = String.format("SELECT @rowNum := @rowNum + 1 num, client.* "
				+ "FROM client, ( SELECT @rowNUm := 0 ) rn " + "WHERE %s LIKE ? %s " + "ORDER BY regDate", searchField,
				pub ? "" : "AND pub = 1");

		return getClientData(selectSql, searchWord);
	}

	@Override
	public int setClientPubTrue(String id) throws SQLException {
		String updateSql = String.format("UPDATE client set pub = 1 WHERE id = ?");

		return setPub(updateSql, id);
	}
}
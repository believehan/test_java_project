package com.psy7758.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.psy7758.dao.CommonModule;
import com.psy7758.dto.ClientInfo;

public class MariaDao extends CommonModule {
	private static final String url = "jdbc:mariadb://localhost:3307/maria_db";

	public MariaDao(String userName, String psw, boolean accessOk) {
		super(url, userName, psw, accessOk);
	}

	@Override
	public ArrayList<ClientInfo> getClients(int pageNum) throws SQLException {
		String querySql = String.format("SELECT * FROM client ORDER BY regNum desc Limit %d,5", pageNum);

		return getClientsDb(querySql);
	}

	@Override
	public int getListCnt() throws SQLException {
		String querySql = "SELECT COUNT(ID) CNT FROM CLIENT";

		return getListCntDb(querySql);
	}
}
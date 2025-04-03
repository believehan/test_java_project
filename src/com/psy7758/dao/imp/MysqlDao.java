package com.psy7758.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.psy7758.dao.CommonModule;
import com.psy7758.dto.ClientInfo;

public class MysqlDao extends CommonModule {
	private static final String url = "jdbc:mysql://localhost:3306/test_db";

	public MysqlDao(String userName, String psw, boolean accessOk) {
		super(url, userName, psw, accessOk);
	}

	// 회원조회 SQL
	@Override
	public ArrayList<ClientInfo> getClients(int pageNum) throws SQLException {

		String querySql = String.format("SELECT * FROM client ORDER BY regNum desc Limit %d,5", pageNum);

		return getClientsDb(querySql);
	}

	// 숫자 세는 메서드
	@Override
	public int getListCnt() throws SQLException {
		String querySql = "SELECT COUNT(ID) CNT FROM CLIENT";

		return getListCntDb(querySql);
	}
}
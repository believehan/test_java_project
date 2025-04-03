package com.psy7758.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.psy7758.dto.ClientInfo;

public interface Dao {
	ArrayList<ClientInfo> getClients(int currentPage) throws SQLException; // 페이징 파라미터 추가.

	public int getListCnt() throws SQLException;

	void close() throws SQLException;
}
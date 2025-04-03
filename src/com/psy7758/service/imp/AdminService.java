package com.psy7758.service.imp;

import java.sql.SQLException;
import java.util.ArrayList;

import com.psy7758.dao.Dao;
import com.psy7758.dto.ClientInfo;
import com.psy7758.service.Service;

public class AdminService implements Service {
	private Dao dao;

	public AdminService(Dao dao) {
		this.dao = dao;
	}

	public int getListCnt() {
		int cnt = 0;

		try {
			cnt = dao.getListCnt();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public ArrayList<ClientInfo> getClients(int pageNum) {
		ArrayList<ClientInfo> clients = null;

		try {
			clients = dao.getClients(pageNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

	public void close() { // dao에서 close메소드 가져오기
		try {
			dao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
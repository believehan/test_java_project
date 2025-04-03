package com.psy7758.service.imp;

import java.util.ArrayList;

import com.psy7758.dao.Dao;
import com.psy7758.dto.ClientInfo;
import com.psy7758.service.Service;

public class UserService implements Service {
	private Dao dao;

	public UserService(Dao dao) {
		this.dao = dao;
	}

	public int getListCnt() {
		int cnt = 0;

		try {
			cnt = dao.getListCnt();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public ArrayList<ClientInfo> getClients(int currentPage) { // 페이징 파라미터 추가.
		ArrayList<ClientInfo> clients = null;

		try {
			clients = dao.getClients(currentPage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clients;
	}

	public void close() {
		try {
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
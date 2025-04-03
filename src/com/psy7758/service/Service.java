package com.psy7758.service;

import java.util.ArrayList;

import com.psy7758.dto.ClientInfo;

public interface Service {
	ArrayList<ClientInfo> getClients(int pageNum);
}
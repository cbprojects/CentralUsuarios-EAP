package com.project.cafe.CentralUsuarios.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IDashboardDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarDashBoardDTO;
import com.project.cafe.CentralUsuarios.service.IDashboardService;

@Service
public class DashboardServiceImpl implements IDashboardService {

	@Autowired
	private IDashboardDao dashboardDao;
	
	
	@Override
	public RequestConsultarDashBoardDTO cargarDashboard(String user) {
		return dashboardDao.consultarDashboard(user);
	}

}

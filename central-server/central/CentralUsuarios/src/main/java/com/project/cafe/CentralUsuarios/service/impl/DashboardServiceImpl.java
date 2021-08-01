package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IBloqueDao;
import com.project.cafe.CentralUsuarios.dao.ICuerpoDao;
import com.project.cafe.CentralUsuarios.dao.IDashboardDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarDashBoardDTO;
import com.project.cafe.CentralUsuarios.model.CuerpoTB;
import com.project.cafe.CentralUsuarios.service.ICuerpoService;
import com.project.cafe.CentralUsuarios.service.IDashboardService;

@Service
public class DashboardServiceImpl implements IDashboardService {

	@Autowired
	private IDashboardDao dashboardDao;
	
	@Autowired
	private IBloqueDao bloqueDao;

	@Override
	public RequestConsultarDashBoardDTO cargarDashboard(String user) {
		return dashboardDao.consultarDashboard(user);
	}

}

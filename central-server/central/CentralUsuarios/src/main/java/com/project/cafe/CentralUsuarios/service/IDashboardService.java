package com.project.cafe.CentralUsuarios.service;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarDashBoardDTO;

public interface IDashboardService {

	/**
	 * 
	 * @param user
	 * @return
	 */
	RequestConsultarDashBoardDTO cargarDashboard(String user);

}

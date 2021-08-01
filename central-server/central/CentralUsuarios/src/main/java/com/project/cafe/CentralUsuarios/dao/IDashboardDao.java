package com.project.cafe.CentralUsuarios.dao;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarDashBoardDTO;

public interface IDashboardDao{

	/**
	 * 
	 * @param idPerfil
	 * @return
	 */
	RequestConsultarDashBoardDTO consultarDashboard(String idPerfil);

}

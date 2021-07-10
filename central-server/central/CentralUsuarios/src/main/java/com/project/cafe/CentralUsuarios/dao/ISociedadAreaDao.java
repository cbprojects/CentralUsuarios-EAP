package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.AreaTB;

public interface ISociedadAreaDao {

	/**
	 * 
	 * @param idSociedad
	 * @return
	 */
	List<AreaTB> buscarAreasActivasPorSociedad(Long idSociedad);

}

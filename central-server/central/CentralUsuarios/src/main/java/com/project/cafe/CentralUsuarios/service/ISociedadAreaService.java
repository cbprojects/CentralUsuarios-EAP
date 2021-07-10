package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.AreaTB;


public interface ISociedadAreaService {

	/**
	 * 
	 * @param idSociedad
	 * @return
	 */
	List<AreaTB> buscarAreasActivasPorSociedad(Long idSociedad);

}

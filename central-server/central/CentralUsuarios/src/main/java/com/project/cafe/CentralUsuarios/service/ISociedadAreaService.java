package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.model.SociedadAreaTB;


public interface ISociedadAreaService {

	/**
	 * 
	 * @param idSociedad
	 * @return
	 */
	List<AreaTB> buscarAreasActivasPorSociedad(Long idSociedad);

	/**
	 * 
	 * @param idSociedad
	 * @param idArea
	 * @return sociedad area 
	 */
	SociedadAreaTB buscarSociedadAreaPorSociedadArea(long idSociedad, long idArea);

}

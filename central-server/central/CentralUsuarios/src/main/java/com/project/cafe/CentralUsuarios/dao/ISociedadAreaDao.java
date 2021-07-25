package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.model.SociedadAreaTB;

public interface ISociedadAreaDao {

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
	 * @return lista de sociedad area disponible
	 */
	List<SociedadAreaTB> buscarSociedadAreaPorSociedadArea(long idSociedad, long idArea);

}

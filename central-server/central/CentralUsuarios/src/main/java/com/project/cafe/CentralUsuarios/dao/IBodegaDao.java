package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.BodegaTB;

public interface IBodegaDao {

	/**
	 * 
	 * @param idSede
	 * @return
	 */
	List<BodegaTB> buscarBodegasActivasPorSede(Long idSede);

}

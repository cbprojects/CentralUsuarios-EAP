package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.BodegaTB;

public interface IBodegaService {

	/**
	 * 
	 * @param idSede
	 * @return
	 */
	List<BodegaTB> buscarBodegasActivasPorSede(Long idSede);

}

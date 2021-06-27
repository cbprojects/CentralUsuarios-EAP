package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.BloqueTB;

public interface IBloqueService {

	/**
	 * 
	 * @param idBodega
	 * @return
	 */
	List<BloqueTB> buscarBloquesActivosPorBodega(Long idBodega);

}

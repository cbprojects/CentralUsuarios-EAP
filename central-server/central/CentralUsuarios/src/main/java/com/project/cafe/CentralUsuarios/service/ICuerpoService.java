package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.CuerpoTB;

public interface ICuerpoService {

	/**
	 * 
	 * @param idBloque
	 * @return
	 */
	List<CuerpoTB> buscarCuerposActivosPorBloque(Long idBloque);

}

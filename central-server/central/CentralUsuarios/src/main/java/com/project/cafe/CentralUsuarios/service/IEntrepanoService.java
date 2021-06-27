package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.EntrepanoTB;

public interface IEntrepanoService {

	/**
	 * 
	 * @param idEstante
	 * @return
	 */
	List<EntrepanoTB> buscarEntrepanosActivosPorEstante(Long idEstante);

}

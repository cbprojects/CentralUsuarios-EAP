package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.EntrepanoTB;

public interface IEntrepanoDao {

	/**
	 * 
	 * @param idEstante
	 * @return
	 */
	List<EntrepanoTB> buscarEntrepanosActivosPorEstante(Long idEstante);

}

package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.BloqueTB;

public interface IBloqueDao {

	/**
	 * 
	 * @param idBodega
	 * @return
	 */
	List<BloqueTB> buscarBloquesActivosPorBodega(Long idBodega);

	void bulkBloque(List<BloqueTB> listaBloque);
}

package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.BloqueTB;
import com.project.cafe.CentralUsuarios.model.BodegaTB;

public interface IBloqueService {

	/**
	 * 
	 * @param idBodega
	 * @return
	 */
	List<BloqueTB> buscarBloquesActivosPorBodega(Long idBodega);

    List<BloqueTB> buildBloques(int cantidadBloques, String usuario, BodegaTB bodegaTB);

	void bulkBloque(List<BloqueTB> listaBlioques);
}

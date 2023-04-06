package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.EntrepanoTB;
import com.project.cafe.CentralUsuarios.model.EstanteTB;

public interface IEntrepanoService {

	/**
	 * 
	 * @param idEstante
	 * @return
	 */
	List<EntrepanoTB> buscarEntrepanosActivosPorEstante(Long idEstante);

	void bulkEntrepanos(List<EntrepanoTB> listaEntrepanos);

	List<EntrepanoTB> buildEntrepanos(int cantidadEntrepanoXEstante, String usuarioCreacion, String usuarioAct, EstanteTB estante);
}

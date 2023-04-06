package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.BloqueTB;
import com.project.cafe.CentralUsuarios.model.CuerpoTB;

public interface ICuerpoService {

	/**
	 * 
	 * @param idBloque
	 * @return
	 */
	List<CuerpoTB> buscarCuerposActivosPorBloque(Long idBloque);

	List<CuerpoTB> buildCuerpos(int cantidadCuerposXBloque, String usuarioCreacion, String usuarioAct, BloqueTB bloque);

	void bulkCuerpo(List<CuerpoTB> listaCuerpo);
}

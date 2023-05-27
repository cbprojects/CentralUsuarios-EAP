package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarBodegasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.BodegaTB;

public interface IBodegaDao {

	/**
	 * 
	 * @param idSede
	 * @return
	 */
	List<BodegaTB> buscarBodegasActivasPorSede(Long idSede);

	ResponseConsultarDTO<BodegaTB> consultarBodegaFiltros(RequestConsultarBodegasDTO filtroBodega);

	BodegaTB crearBodega(BodegaTB bodega);

	List<BodegaTB> buscarBodegaPorCodigo(String codigo);

	BodegaTB modificarBodega(BodegaTB bodega);
}

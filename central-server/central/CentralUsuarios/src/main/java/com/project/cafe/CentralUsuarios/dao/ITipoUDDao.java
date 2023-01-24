package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.TipoUDTB;

public interface ITipoUDDao {

	/*
	 * Metodo para crear tipo
	 */
	TipoUDTB crearTipoUD(TipoUDTB tipo);

	/*
	 * Metodo para modificar tipo
	 */
	TipoUDTB modificarTipoUD(TipoUDTB tipo);
	
	/*
	 * Metodo para consultar los tipo por codigo
	 */
	List<TipoUDTB> buscarTipoUdPorCodigo(String codigo);
	/**
	 * 
	 * @return
	 */
	List<TipoUDTB> buscarTipoUDActivos();

	/**
	 *  método que consulta los tipo de manera paginada
	 * @param filtroRol
	 * @return
	 */
	ResponseConsultarDTO<TipoUDTB> consultarTipoUDFiltros(RequestConsultarMasivoDTO request);

}

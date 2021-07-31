package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarUnidadDocumentalDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;

public interface IUnidadDocumentalService {

	/*
	 * Metodo para crear UnidadDocumental
	 */
	UnidadDocumentalTB crearUnidadDocumental(UnidadDocumentalTB unidadDocumental);

	/*
	 * Metodo para modificar UnidadDocumental
	 */
	UnidadDocumentalTB modificarUnidadDocumental(UnidadDocumentalTB unidadDocumental);

	/*
	 * Metodo para buscar unidad documental por codigo y sociedad
	 */
	List<UnidadDocumentalTB> buscarUnidadDocumentalPorCodigoSociedad(String codigo, long idSociedad);
	
	/**
	 * 
	 * @param filtroUnidadDocumental
	 * @return lista de unidades documentales
	 */
	ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalFiltros(RequestConsultarUnidadDocumentalDTO filtroUnidadDocumental);

	/*
	 * Metodo para buscar unidad documental por id
	 */
	UnidadDocumentalTB buscarUnidadDocumentalPorId(long idUnidadDocumental);

	/*
	 * Metodo para buscar unidad documental por caja
	 */
	List<UnidadDocumentalTB> RequestConsultarUnidadDocumentalPorCaja(Long idCaja);

	/*
	 * Metodo para cambiar de caja las unidades documentales
	 */
	void cambiarCajaUnidadDocumentalMasivo(List<UnidadDocumentalTB> lstUnidadDocumentalCajaUno, Long idCajaUno);

}

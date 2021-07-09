package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarCajasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;

public interface ICajaService {

	/*
	 * Metodo para crear cajas
	 */
	CajaTB  crearCaja(CajaTB caja);

	/*
	 * Metodo para modificar caja
	 */
	CajaTB modificarCaja(CajaTB caja);

	/*
	 * Metodo para buscar caja por codigo y sociedad
	 */
	List<CajaTB> buscarcajaPorCodigoSociedad(String codigocaja, long idSociedad);
	
	/**
	 * 
	 * @param filtroCaja
	 * @return
	 */
	ResponseConsultarDTO<CajaTB> consultarCajasFiltros(RequestConsultarCajasDTO filtroCaja);

}

package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarCajasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.model.ClienteTB;

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
	List<CajaTB> buscarcajaPorCodigoCliente(String codigocaja, long idCliente);
	
	/**
	 * 
	 * @param filtroCaja
	 * @return
	 */
	ResponseConsultarDTO<CajaTB> consultarCajasFiltros(RequestConsultarCajasDTO filtroCaja);

	/**
	 * 
	 * @param filtroCaja
	 * @return caja inicial segun la sociedad enviada
	 */
	CajaTB retornarCajaInicialPorCliente(ClienteTB cliente);

	/**
	 * 
	 * @param idSociedad
	 * @return lista cajas  segun la sociedad enviada
	 */
	List<CajaTB> consultarCajasPorCliente(Long idCliente);

}

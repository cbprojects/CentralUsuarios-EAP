package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarCajasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;

@EnableTransactionManagement
public interface ICajaDao {
	
	/*
	 * Metodo para crear Caja
	 */
	CajaTB crearCaja(CajaTB Caja);

	/*
	 * Metodo para modificar Caja
	 */
	CajaTB modificarCaja(CajaTB Caja);
	
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

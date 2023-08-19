package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ClienteTB;

public interface IClienteDao {

	
	/*
	 * Metodo para crear cliente
	 */
	ClienteTB crearCliente(ClienteTB cliente);

	/*
	 * Metodo para modificar cliente
	 */
	ClienteTB modificarCliente(ClienteTB cliente);
	
	/*
	 * Metodo para consultar los cliente por codigo
	 */
	List<ClienteTB> buscarClientePorCodigo(String codigoRol);
	/**
	 * 
	 * @return
	 */
	List<ClienteTB> buscarClientesActivos();
	
	/**
	 *  método que consulta los cliente de manera paginada
	 * @param filtroRol
	 * @return
	 */
	ResponseConsultarDTO<ClienteTB> consultarClienteFiltros(RequestConsultarMasivoDTO request);

	/**
	 *  método que consulta numero de factura del cliente
	 * @param filtroRol
	 * @return
	 */
	Integer buscarClientePorIDNumeroFactura(long id);

}

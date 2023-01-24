package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ClienteTB;

public interface IClienteService {

	/*
	 * Metodo para crear cliente
	 */
	MasivoDTO  crearCliente(MasivoDTO masivo);

	/*
	 * Metodo para modificar cliente
	 */
	MasivoDTO modificarCliente(MasivoDTO masivo);

	/*
	 * Metodo para buscar cliente por nombre
	 */
	List<ClienteTB> buscarClientePorCodigo(String nombre);
	
	/**
	 * 
	 * @return
	 */
	List<ClienteTB> buscarClientesActivos();
	
	/**
	 * 
	 * @return
	 */
	ResponseConsultarDTO<MasivoDTO> consultarClienteFiltros(RequestConsultarMasivoDTO request);

}

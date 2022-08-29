package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.ClienteTB;

public interface IClienteService {

	/**
	 * 
	 * @return
	 */
	List<ClienteTB> buscarClientesActivos();

}

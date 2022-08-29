package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.ClienteTB;

public interface IClienteDao {

	/**
	 * 
	 * @return
	 */
	List<ClienteTB> buscarClientesActivos();

}

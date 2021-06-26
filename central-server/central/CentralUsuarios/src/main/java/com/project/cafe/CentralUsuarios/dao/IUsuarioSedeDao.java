package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.SedeTB;

public interface IUsuarioSedeDao {

	/**
	 * 
	 * @param email
	 * @return
	 */
	List<SedeTB> buscarSedesActivasPorUsuario(String email);

}

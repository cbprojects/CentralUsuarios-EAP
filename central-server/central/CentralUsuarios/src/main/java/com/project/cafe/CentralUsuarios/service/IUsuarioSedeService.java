package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.SedeTB;

public interface IUsuarioSedeService {

	/**
	 * 
	 * @param email
	 * @return
	 */
	List<SedeTB> buscarSedesActivasPorUsuario(String email);

}

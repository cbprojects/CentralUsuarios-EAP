package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.SociedadTB;

public interface ISociedadService {


	/*
	 * Metodo para consultar las sociedades activas
	 */
	List<SociedadTB> buscarSociedadActiva();
	
}

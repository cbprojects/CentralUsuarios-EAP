package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.RolTB;

public interface IRolService {

	/*
	 * Metodo para crear roles
	 */
	RolTB  crearRol(RolTB rol);

	/*
	 * Metodo para modificar rol
	 */
	RolTB modificarRol(RolTB rol);

	/*
	 * Metodo para buscar rol por codigo
	 */
	List<RolTB> buscarRolPorCodigo(String codigoRol);

}

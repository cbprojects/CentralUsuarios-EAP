package com.project.cafe.CentralUsuarios.service;

import com.project.cafe.CentralUsuarios.model.RolTB;

public interface IRolService {

	/*
	 * Metodo para crear roles
	 */
	public RolTB  crearRol(RolTB rol);

	/*
	 * Metodo para modificar rol
	 */
	public RolTB modificarRol(RolTB rol);

}

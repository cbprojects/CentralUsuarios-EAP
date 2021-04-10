package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.RolTB;

@EnableTransactionManagement
public interface IRolDao {
	
	/*
	 * Metodo para crear rol
	 */
	RolTB crearRol(RolTB rol);

	/*
	 * Metodo para modificar rol
	 */
	RolTB modificarRol(RolTB rol);
	
	/*
	 * Metodo para consultar los roles por codigo
	 */
	List<RolTB> buscarRolPorCodigo(String codigoRol);

}

package com.project.cafe.CentralUsuarios.dao;


import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.model.RolPerfilTB;

@EnableTransactionManagement
public interface IRolPerfilDao {

	/*
	 * Metodo para crear RolPerfil
	 */
	RolPerfilTB crearRolPerfil(RolPerfilTB rolPerfil);

	/*
	 * Metodo para eliminarMasivo RolPerfil
	 */
	void eliminarRolPerfilMasivoXPerfil(Long id);

	/*
	 * Metodo para Buscar roles segun el perfil 
	 */
	List<RolPerfilTB> BuscarRolesSegunPerfil(long id);

	
}

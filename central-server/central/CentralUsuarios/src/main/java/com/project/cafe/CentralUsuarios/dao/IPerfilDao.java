package com.project.cafe.CentralUsuarios.dao;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.model.PerfilTB;

@EnableTransactionManagement
public interface IPerfilDao {

	/*
	 * Metodo para crear perfil
	 */
	PerfilTB crearPerfil(PerfilTB perfilAutor);

	/*
	 * Metodo para modificar perfil
	 */
	PerfilTB modificarPerfil(PerfilTB perfilAutor);

	

}

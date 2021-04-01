package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.PerfilTB;


public interface IPerfilService {

	/*
	 * Metodo para consultar los perfiles por codigo
	 */
	public List<PerfilTB> buscarPerfilPorCodigo(String codigoPerfil);
	
	/*
	 * Metodo para crear perfil
	 */
	PerfilTB crearPerfil(PerfilTB perfilAutor);

	/*
	 * Metodo para modificar perfil
	 */
	PerfilTB modificarPerfil(PerfilTB perfilAutor);

}

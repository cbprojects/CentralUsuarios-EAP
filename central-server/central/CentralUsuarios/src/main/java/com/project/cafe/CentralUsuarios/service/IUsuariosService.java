package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.model.UsuarioAutorTB;

public interface IUsuariosService {

	/*
	 * Metodo para consultar usuarios
	 */
	public List<UsuarioAutorTB> consultarUsuarios();

	/*
	 * Metodo para consultar los usuarios por filtros ordenados por mas recientes
	 */
	public List<UsuarioAutorTB> consultarUsuariosPorFiltros(UsuarioAutorTB filtroUsuario);

	/*
	 * Metodo para crear Usuarios
	 */
	public UsuarioAutorTB crearUsuario(UsuarioAutorTB usuarioAutor);

	/*
	 * Metodo para Login
	 */
	public UsuarioAutorTB loginUsuario(String usuario, String password);

	/*
	 * Metodo para modificar usuario
	 */
	public UsuarioAutorTB modificarUsuario(UsuarioAutorTB usuarioAutor);

}

package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.model.UsuarioAutorTB;

@EnableTransactionManagement
public interface IUsuariosDao {

	/*
	 * Metodo para consultar usuarios
	 */
	List<UsuarioAutorTB> consultarUsuariosPorUsuario(String usuario);

	/*
	 * Metodo para consultar los usuarios por filtros ordenados por más recientes
	 */
	List<UsuarioAutorTB> consultarUsuariosPorFiltros(UsuarioAutorTB filtroUsuario);

	/*
	 * Metodo para crear usuario
	 */
	UsuarioAutorTB crearUsuario(UsuarioAutorTB usuarioAutor);

	/*
	 * Metodo para modificar usuario
	 */
	UsuarioAutorTB modificarUsuario(UsuarioAutorTB usuarioAutor);

	/*
	 * Metodo para eliminar usuario
	 */
	void eliminarUsuario(long idUsuario);

	/*
	 * Metodo para consultar usuarios
	 */
	List<UsuarioAutorTB> consultarUsuarios();

}

package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuariosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;

@EnableTransactionManagement
public interface IUsuarioDao {
	
	/*
	 * Metodo para crear usuario
	 */
	UsuarioTB crearUsuario(UsuarioTB usuario);

	/*
	 * Metodo para modificar usuario
	 */
	UsuarioTB modificarUsuario(UsuarioTB usuario);
	
	/*
	 * Metodo para consultar los usuarios por nick
	 */
	List<UsuarioTB> buscarUsuarioPorEmail(String email);
	
	/**
	 *  método que consulta los usuarios de manera paginada
	 * @param filtroRol
	 * @return
	 */
	ResponseConsultarDTO<UsuarioTB> consultarUsuariosPorFiltros(RequestConsultarUsuariosDTO filtroUsuario) throws Exception;

}

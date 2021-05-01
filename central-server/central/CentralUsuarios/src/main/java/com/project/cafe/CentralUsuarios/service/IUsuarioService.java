package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuariosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;

public interface IUsuarioService {

	/*
	 * Metodo para crear usuarios
	 */
	UsuarioTB crearUsuario(UsuarioTB usuario) throws Exception;

	/*
	 * Metodo para modificar usuarios
	 */
	UsuarioTB modificarUsuario(UsuarioTB usuario);

	/*
	 * Metodo para buscar usuarios por nick
	 */
	List<UsuarioTB> buscarUsuarioPorEmail(String nickEmail);

	/**
	 * 
	 * @param filtroUsuario
	 * @return
	 */
	ResponseConsultarDTO<UsuarioTB> consultarUsusarioFiltros(RequestConsultarUsuariosDTO filtroUsuario) throws Exception;

}

package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarPerfilesDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
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
	
	/**
	 *  método que consulta los perfiles de manera paginada
	 * @param filtroPerfil
	 * @return
	 */
	ResponseConsultarDTO<PerfilTB> consultarPerfilesPorFiltros(RequestConsultarPerfilesDTO filtroPerfil);

}

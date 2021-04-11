package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarPerfilesDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
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

	/*
	 * Metodo para consultar los perfiles por codigo
	 */
	List<PerfilTB> buscarPerfilPorCodigo(String codigoPerfil);

	/**
	 *  método que consulta los perfiles de manera paginada
	 * @param filtroPerfil
	 * @return
	 */
	ResponseConsultarDTO<PerfilTB> consultarPerfilesPorFiltros(RequestConsultarPerfilesDTO filtroPerfil);
	
}

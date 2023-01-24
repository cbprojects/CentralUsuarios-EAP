package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ContenedorUDTB;

public interface IContenedorDao {

	/*
	 * Metodo para crear contenedor
	 */
	ContenedorUDTB crearContenedor(ContenedorUDTB contenedor);

	/*
	 * Metodo para modificar contenedor
	 */
	ContenedorUDTB modificarContenedor(ContenedorUDTB contenedor);
	
	/*
	 * Metodo para consultar los contenedor por codigo
	 */
	List<ContenedorUDTB> buscarContenedorPorCodigo(String codigo);
	/**
	 * 
	 * @return
	 */
	List<ContenedorUDTB> buscarContenedoresActivos();

	/**
	 *  método que consulta los contenedor de manera paginada
	 * @param filtroRol
	 * @return
	 */
	ResponseConsultarDTO<ContenedorUDTB> consultarContenedorFiltros(RequestConsultarMasivoDTO request);
}

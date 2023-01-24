package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ContenedorUDTB;

public interface IContenedorService {

	/*
	 * Metodo para crear Contenedor
	 */
	MasivoDTO  crearContenedor(MasivoDTO masivo);

	/*
	 * Metodo para modificar Contenedor
	 */
	MasivoDTO modificarContenedor(MasivoDTO masivo);

	/*
	 * Metodo para buscar Contenedor por nombre
	 */
	List<ContenedorUDTB> buscarContenedorPorCodigo(String nombre);
	/**
	 * 
	 * @return
	 */
	List<ContenedorUDTB> buscarContenedoresActivos();
	
	/**
	 * 
	 * @return
	 */
	ResponseConsultarDTO<MasivoDTO> consultarContenedorFiltros(RequestConsultarMasivoDTO request);

}

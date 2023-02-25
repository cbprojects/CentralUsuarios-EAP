package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.AreaTB;


public interface IAreaService {

	/*
	 * Metodo para crear area
	 */
	MasivoDTO  crearArea(MasivoDTO masivo);

	/*
	 * Metodo para modificar area
	 */
	MasivoDTO modificarArea(MasivoDTO masivo);

	/*
	 * Metodo para buscar area por nombre
	 */
	List<AreaTB> buscarAreaPorCodigo(String nombre);
	
	/**
	 * 
	 * @param filtroRol
	 * @return
	 */
	ResponseConsultarDTO<MasivoDTO> consultarAreaFiltros(RequestConsultarMasivoDTO request);

	List<AreaTB> consultarAreasActiva();

}

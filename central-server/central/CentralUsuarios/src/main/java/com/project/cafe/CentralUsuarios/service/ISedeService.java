package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SedeTB;


public interface ISedeService {

	/*
	 * Metodo para crear area
	 */
	MasivoDTO  crearSede(MasivoDTO masivo);

	/*
	 * Metodo para modificar area
	 */
	MasivoDTO modificarSede(MasivoDTO masivo);

	/*
	 * Metodo para buscar area por nombre
	 */
	List<SedeTB> buscarSedePorCodigo(String nombre);
	
	/**
	 * 
	 * @param filtroRol
	 * @return
	 */
	ResponseConsultarDTO<MasivoDTO> consultarSedeFiltros(RequestConsultarMasivoDTO request);

}

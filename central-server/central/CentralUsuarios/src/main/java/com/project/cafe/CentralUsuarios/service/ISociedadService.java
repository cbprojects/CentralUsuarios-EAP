package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarSociedadDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SociedadTB;

public interface ISociedadService {

	/*
	 * Metodo para consultar las sociedades activas
	 */
	List<SociedadTB> buscarSociedadActiva();
	
	/*
	 * Metodo para consultar las sociedades activas
	 */
	SociedadTB crearSociedad(SociedadTB sociedad);
	
	/*
	 * Metodo para consultar las sociedades activas
	 */
	SociedadTB modificarSociedad(SociedadTB sociedad);

	/*
	 * Metodo para consultar las sociedades activas
	 */
	List<SociedadTB> buscarRolPorNombre(String nombre);

	/*
	 * Metodo para consultar las sociedades activas
	 */
	ResponseConsultarDTO<SociedadTB> consultarSociedadFiltros(RequestConsultarSociedadDTO request);

	List<SociedadTB> consultarSociedadActivaPorCliente(Long idCliente);
	
}

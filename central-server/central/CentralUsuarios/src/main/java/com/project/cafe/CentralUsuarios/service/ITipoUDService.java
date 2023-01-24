package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.TipoUDTB;

public interface ITipoUDService {

	/*
	 * Metodo para crear tipoUd
	 */
	MasivoDTO  crearTipoUD(MasivoDTO masivo);

	/*
	 * Metodo para modificar tipoUd
	 */
	MasivoDTO modificarTipoUd(MasivoDTO masivo);

	/*
	 * Metodo para buscar tipoUd por nombre
	 */
	List<TipoUDTB> buscarTipoUdPorCodigo(String nombre);
	
	/**
	 * 
	 * @return
	 */
	List<TipoUDTB> buscarTipoUDActivos();

	/**
	 * 
	 * @return
	 */
	ResponseConsultarDTO<MasivoDTO> consultarTipoUDFiltros(RequestConsultarMasivoDTO request);
}

package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.AreaTB;

@EnableTransactionManagement
public interface IAreaDao {
	
	/*
	 * Metodo para crear area
	 */
	AreaTB crearArea(AreaTB area);

	/*
	 * Metodo para modificar area
	 */
	AreaTB modificarArea(AreaTB area);
	
	/*
	 * Metodo para consultar los area por codigo
	 */
	List<AreaTB> buscarAreaPorCodigo(String codigoRol);
	
	/*
	 * Metodo para consultar los area por id
	 */
	List<AreaTB> buscarAreaPorId(Long id);
	
	/**
	 *  método que consulta los area de manera paginada
	 * @param filtroRol
	 * @return
	 */
	ResponseConsultarDTO<AreaTB> consultarAreaFiltros(RequestConsultarMasivoDTO request);

}

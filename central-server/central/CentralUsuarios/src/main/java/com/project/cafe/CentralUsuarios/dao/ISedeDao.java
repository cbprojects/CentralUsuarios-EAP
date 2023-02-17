package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SedeTB;

@EnableTransactionManagement
public interface ISedeDao {
	
	/*
	 * Metodo para crear sede
	 */
	SedeTB crearSede(SedeTB area);

	/*
	 * Metodo para modificar sede
	 */
	SedeTB modificarSede(SedeTB area);
	
	/*
	 * Metodo para consultar los sede por codigo
	 */
	List<SedeTB> buscarSedePorCodigo(String codigoRol);
	
	
	/**
	 *  método que consulta los area de manera paginada
	 * @param filtroRol
	 * @return
	 */
	ResponseConsultarDTO<SedeTB> consultarSedeFiltros(RequestConsultarMasivoDTO request);

}

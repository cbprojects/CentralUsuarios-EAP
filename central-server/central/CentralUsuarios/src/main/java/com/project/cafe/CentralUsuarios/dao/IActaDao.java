package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarActaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ActaTB;

@EnableTransactionManagement
public interface IActaDao {
	
	/*
	 * Metodo para crear Acta
	 */
	ActaTB crearActa(ActaTB acta);

	/*
	 * Metodo para modificar Acta
	 */
	ActaTB modificarActa(ActaTB acta);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	ResponseConsultarDTO<ActaTB> consultarActaFiltros(RequestConsultarActaDTO request);
	
	/**
	 * 
	 * @param idUD
	 * @return
	 */

	List<ActaTB> buscarActaPorId(Long idUD);

}

package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarSociedadDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SociedadTB;

@EnableTransactionManagement
public interface ISociedadDao {
	
	/*
	 * Metodo para consultar las sociedades activas
	 */
	List<SociedadTB> buscarSociedadActiva();

	SociedadTB crearSociedad(SociedadTB sociedad);

	SociedadTB modificarSociedad(SociedadTB sociedad);

	List<SociedadTB> buscarRolPorNombre(String nombre);

	ResponseConsultarDTO<SociedadTB> consultarSociedadFiltros(RequestConsultarSociedadDTO request);

	List<SociedadTB> consultarSociedadActivaPorCliente(Long idCliente);
	
	

}

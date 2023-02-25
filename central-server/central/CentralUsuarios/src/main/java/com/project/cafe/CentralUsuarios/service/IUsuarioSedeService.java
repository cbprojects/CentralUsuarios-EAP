package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuarioSedeDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.model.UsuarioSedeTB;

public interface IUsuarioSedeService {

	/**
	 * 
	 * @param email
	 * @return
	 */
	List<SedeTB> buscarSedesActivasPorUsuario(String email);
	
	UsuarioSedeTB modificarUsuarioSede(UsuarioSedeTB newUsuarioSede);
	
	UsuarioSedeTB crearUsuarioSede(UsuarioSedeTB newUsuarioSede);
	
	List<UsuarioSedeTB> buscarUsuarioSedePorId(long idU, long idS);

	ResponseConsultarDTO<UsuarioSedeTB> consultarUsuarioSedeFiltros(RequestConsultarUsuarioSedeDTO request);

}

package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuarioSedeDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.model.UsuarioSedeTB;

public interface IUsuarioSedeDao {

	/**
	 * 
	 * @param email
	 * @return
	 */
	List<SedeTB> buscarSedesActivasPorUsuario(String email);

	UsuarioSedeTB crearUsuarioSede(UsuarioSedeTB newUsuarioSede);

	UsuarioSedeTB modificarUsuarioSede(UsuarioSedeTB newUsuarioSede);

	List<UsuarioSedeTB> buscarUsuarioSedePorId(long idU, long idS);

	ResponseConsultarDTO<UsuarioSedeTB> consultarUsuarioSedeFiltros(RequestConsultarUsuarioSedeDTO request);

}

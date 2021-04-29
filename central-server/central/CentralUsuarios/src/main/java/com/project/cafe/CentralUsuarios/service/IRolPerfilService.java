package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarPerfilesDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.RolPerfilTB;
import com.project.cafe.CentralUsuarios.model.RolTB;


public interface IRolPerfilService {

	/*
	 * Metodo para crear RolPerfil
	 */
	RolPerfilTB crearRolPerfil(RolPerfilTB rolPerfil);
	
	/*
	 * Metodo para eliminarMasivo RolPerfil
	 */
	void eliminarRolPerfilMasivoXPerfil(Long id);

	/*
	 * Metodo para Buscar roles segun el perfil 
	 */
	
	List<RolTB> BuscarRolesSegunPerfil(long id);

	/*
	 * Metodo para Buscar roles no Asociados segun el perfil 
	 */
	List<RolTB> BuscarRolesNoAsociadosSegunPerfil(long id);


}

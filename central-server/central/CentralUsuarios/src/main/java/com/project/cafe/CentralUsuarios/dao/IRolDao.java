package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarRolesDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.RolTB;

@EnableTransactionManagement
public interface IRolDao {
	
	/*
	 * Metodo para crear rol
	 */
	RolTB crearRol(RolTB rol);

	/*
	 * Metodo para modificar rol
	 */
	RolTB modificarRol(RolTB rol);
	
	/*
	 * Metodo para consultar los roles por codigo
	 */
	List<RolTB> buscarRolPorCodigo(String codigoRol);
	
	/**
	 *  método que consulta los roles de manera paginada
	 * @param filtroRol
	 * @return
	 */
	ResponseConsultarDTO<RolTB> consultarRolesPorFiltros(RequestConsultarRolesDTO filtroRol);

	/**
	 *  método que consulta los roles que no esten asociados a el perfil enviado
	 * @param id
	 * @return
	 */
	List<RolTB> BuscarRolesNoAsociadosSegunPerfil(long id);

}

package com.project.cafe.CentralUsuarios.dao;


import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.dto.RequestBuscarPermisosBodegaUsuarioDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarPermisosBodegaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.PermisosBodegaTB;


@EnableTransactionManagement
public interface IPermisosBodegaDao {

	PermisosBodegaTB crearPermisosBodega(PermisosBodegaTB permisosBodega);

	PermisosBodegaTB modificarPermisosBodega(PermisosBodegaTB permisosBodega);

	List<PermisosBodegaTB> buscarPermisosPorBodegaUsuario(long idBodega, long idUsuario);

	ResponseConsultarDTO<PermisosBodegaTB> consultarPermisosBodegaFiltros(RequestConsultarPermisosBodegaDTO request);

	List<PermisosBodegaTB> consultarPermisosUsuarioBodega(RequestBuscarPermisosBodegaUsuarioDTO request);
	
}

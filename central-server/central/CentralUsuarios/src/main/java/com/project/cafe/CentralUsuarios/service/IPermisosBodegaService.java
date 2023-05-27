package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestBuscarPermisosBodegaUsuarioDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarPermisosBodegaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.PermisosBodegaTB;

public interface IPermisosBodegaService {

	PermisosBodegaTB crearPermisosBodega(PermisosBodegaTB permisosBodega);

	PermisosBodegaTB modificarPermisosBodega(PermisosBodegaTB permisosBodega);

	List<PermisosBodegaTB> buscarPermisosPorBodegaUsuario(long idBodega, long idUsuario);

	ResponseConsultarDTO<PermisosBodegaTB> consultarPermisosBodegaFiltros(RequestConsultarPermisosBodegaDTO request);

	List<PermisosBodegaTB> consultarPermisosUsuarioBodega(RequestBuscarPermisosBodegaUsuarioDTO request);

}

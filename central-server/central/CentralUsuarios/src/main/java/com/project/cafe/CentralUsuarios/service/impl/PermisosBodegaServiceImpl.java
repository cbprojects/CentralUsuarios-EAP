package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IPermisosBodegaDao;
import com.project.cafe.CentralUsuarios.dto.RequestBuscarPermisosBodegaUsuarioDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarPermisosBodegaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.PermisosBodegaTB;
import com.project.cafe.CentralUsuarios.service.IPermisosBodegaService;

@Service
public class PermisosBodegaServiceImpl implements IPermisosBodegaService {

	@Autowired
	private IPermisosBodegaDao permisosBodegaDao;
	
	

	@Transactional
	@Override
	public PermisosBodegaTB crearPermisosBodega(PermisosBodegaTB permisosBodega) {
		return permisosBodegaDao.crearPermisosBodega(permisosBodega);
	}

	@Transactional
	@Override
	public PermisosBodegaTB modificarPermisosBodega(PermisosBodegaTB permisosBodega) {
		return permisosBodegaDao.modificarPermisosBodega(permisosBodega);
	}

	@Override
	public List<PermisosBodegaTB> buscarPermisosPorBodegaUsuario(long idBodega, long idUsuario) {
		return permisosBodegaDao.buscarPermisosPorBodegaUsuario(idBodega,idUsuario);
	}

	@Override
	public ResponseConsultarDTO<PermisosBodegaTB> consultarPermisosBodegaFiltros(RequestConsultarPermisosBodegaDTO request) {
		return permisosBodegaDao.consultarPermisosBodegaFiltros(request);
	}

	@Override
	public List<PermisosBodegaTB> consultarPermisosUsuarioBodega(RequestBuscarPermisosBodegaUsuarioDTO request) {
		return permisosBodegaDao.consultarPermisosUsuarioBodega(request);
	}
	
		
	
	

}

package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IPerfilDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarPerfilesDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.service.IPerfilService;

@Service
public class PerfilServiceImpl implements IPerfilService {

	@Autowired
	private IPerfilDao perfilDAO;

	@Transactional
	@Override
	public PerfilTB crearPerfil(PerfilTB perfil) {
		return perfilDAO.crearPerfil(perfil);
	}

	@Transactional
	@Override
	public PerfilTB modificarPerfil(PerfilTB perfil) {
		return perfilDAO.modificarPerfil(perfil);
	}

	@Override
	public List<PerfilTB> buscarPerfilPorCodigo(String codigoPerfil) {
		return perfilDAO.buscarPerfilPorCodigo(codigoPerfil);
	}

	@Override
	public ResponseConsultarDTO<PerfilTB> consultarPerfilesPorFiltros(RequestConsultarPerfilesDTO filtroPerfil) {
		return perfilDAO.consultarPerfilesPorFiltros(filtroPerfil);
	}
	
	public List<PerfilTB> consultarPerfilesActivos(){
		return perfilDAO.consultarPerfilesActivos();
	}

}

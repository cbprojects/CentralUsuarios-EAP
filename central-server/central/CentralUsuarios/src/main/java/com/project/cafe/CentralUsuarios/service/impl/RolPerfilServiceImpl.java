package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IRolDao;
import com.project.cafe.CentralUsuarios.dao.IRolPerfilDao;
import com.project.cafe.CentralUsuarios.model.RolPerfilTB;
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.service.IRolPerfilService;

@Service
public class RolPerfilServiceImpl implements IRolPerfilService {

	@Autowired
	private IRolPerfilDao rolPerfilDAO;
	
	@Autowired
	private IRolDao rolDAO;

	@Transactional
	@Override
	public RolPerfilTB crearRolPerfil(RolPerfilTB rolPerfil) {
		return rolPerfilDAO.crearRolPerfil(rolPerfil);
	}

	@Override
	public void eliminarRolPerfilMasivoXPerfil(Long id) {
		rolPerfilDAO.eliminarRolPerfilMasivoXPerfil(id);
	}

	@Override
	public List<RolTB> BuscarRolesSegunPerfil(long id) {
		List<RolTB> lstRol= new ArrayList<RolTB>();
		List <RolPerfilTB> lstRolPerfil = new ArrayList<RolPerfilTB>();
		lstRolPerfil=rolPerfilDAO.BuscarRolesSegunPerfil(id);
		if(lstRolPerfil != null && !lstRolPerfil.isEmpty() ) {
			for (RolPerfilTB rolPerfilTB : lstRolPerfil) {
				lstRol.add(rolPerfilTB.getRol());
			}
		}
		return lstRol;
	}

	@Override
	public List<RolTB> BuscarRolesNoAsociadosSegunPerfil(long id) {
		return rolDAO.BuscarRolesNoAsociadosSegunPerfil(id);
	}
	
}

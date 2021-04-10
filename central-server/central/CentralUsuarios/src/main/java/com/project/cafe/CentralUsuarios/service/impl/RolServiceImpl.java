package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IRolDao;
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.service.IRolService;

@Service
public class RolServiceImpl implements IRolService {

	@Autowired
	private IRolDao rolDAO;

	@Transactional
	@Override
	public RolTB crearRol(RolTB rol) {
		return rolDAO.crearRol(rol);
	}

	@Transactional
	@Override
	public RolTB modificarRol(RolTB rol) {
		return rolDAO.modificarRol(rol);
	}
	
	@Override
	public List<RolTB> buscarRolPorCodigo(String codigoRol) {
		return rolDAO.buscarRolPorCodigo(codigoRol);
	}

}

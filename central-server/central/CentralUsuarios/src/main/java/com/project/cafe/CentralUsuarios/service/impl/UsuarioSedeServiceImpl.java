package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IUsuarioSedeDao;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.service.IUsuarioSedeService;

@Service
public class UsuarioSedeServiceImpl implements IUsuarioSedeService {

	@Autowired
	private IUsuarioSedeDao usuarioSedeDao;
	
	@Override
	public List<SedeTB> buscarSedesActivasPorUsuario(String email) {
		return usuarioSedeDao.buscarSedesActivasPorUsuario(email);
	}

}

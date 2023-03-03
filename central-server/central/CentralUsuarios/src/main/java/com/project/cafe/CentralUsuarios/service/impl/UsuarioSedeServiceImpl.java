package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IUsuarioSedeDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuarioSedeDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.model.UsuarioSedeTB;
import com.project.cafe.CentralUsuarios.service.IUsuarioSedeService;

@Service
public class UsuarioSedeServiceImpl implements IUsuarioSedeService {

	@Autowired
	private IUsuarioSedeDao usuarioSedeDao;
	
	@Override
	public List<SedeTB> buscarSedesActivasPorUsuario(String email) {
		return usuarioSedeDao.buscarSedesActivasPorUsuario(email);
	}
	
	@Override
	@Transactional
	public UsuarioSedeTB crearUsuarioSede(UsuarioSedeTB newUsuarioSede) {
		return usuarioSedeDao.crearUsuarioSede(newUsuarioSede);
	}

	@Override
	@Transactional
	public UsuarioSedeTB modificarUsuarioSede(UsuarioSedeTB newUsuarioSede) {
		return usuarioSedeDao.modificarUsuarioSede(newUsuarioSede);
	}

	@Override
	public List<UsuarioSedeTB> buscarUsuarioSedePorId(long idU, long idS) {
		return usuarioSedeDao.buscarUsuarioSedePorId(idU,idS);
	}

	@Override
	public ResponseConsultarDTO<UsuarioSedeTB> consultarUsuarioSedeFiltros(RequestConsultarUsuarioSedeDTO request) {
		return usuarioSedeDao.consultarUsuarioSedeFiltros(request);
	}

}

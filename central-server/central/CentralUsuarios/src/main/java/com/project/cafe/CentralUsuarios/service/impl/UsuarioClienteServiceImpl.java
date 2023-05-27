package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IUsuarioClienteDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuarioClienteDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.model.UsuarioClienteTB;
import com.project.cafe.CentralUsuarios.service.IUsuarioClienteService;

@Service
public class UsuarioClienteServiceImpl implements IUsuarioClienteService {

	@Autowired
	private IUsuarioClienteDao usuarioClienteDao;
	
	@Override
	public List<ClienteTB> buscarClientesActivosPorUsuario(long idU) {
		return usuarioClienteDao.buscarClientesActivosPorUsuario(idU);
	}
	
	@Override
	@Transactional
	public UsuarioClienteTB crearUsuarioCliente(UsuarioClienteTB newUsuarioCliente) {
		return usuarioClienteDao.crearUsuarioCliente(newUsuarioCliente);
	}

	@Override
	@Transactional
	public UsuarioClienteTB modificarUsuarioCliente(UsuarioClienteTB newUsuarioCliente) {
		return usuarioClienteDao.modificarUsuarioCliente(newUsuarioCliente);
	}

	@Override
	public List<UsuarioClienteTB> buscarUsuarioClientePorId(long idU, long idS) {
		return usuarioClienteDao.buscarUsuarioClientePorId(idU,idS);
	}

	@Override
	public ResponseConsultarDTO<UsuarioClienteTB> consultarUsuarioClienteFiltros(RequestConsultarUsuarioClienteDTO request) {
		return usuarioClienteDao.consultarUsuarioClienteFiltros(request);
	}

}

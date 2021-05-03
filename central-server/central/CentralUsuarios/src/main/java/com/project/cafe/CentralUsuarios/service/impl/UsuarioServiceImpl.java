package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IUsuarioDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuariosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;
import com.project.cafe.CentralUsuarios.service.IUsuarioService;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDAO;

	@Transactional
	@Override
	public UsuarioTB crearUsuario(UsuarioTB usuario) throws Exception {
		return usuarioDAO.crearUsuario(usuario);
	}

	@Transactional
	@Override
	public UsuarioTB modificarUsuario(UsuarioTB usuario) {
		return usuarioDAO.modificarUsuario(usuario);
	}
	
	@Override
	public List<UsuarioTB> buscarUsuarioPorEmail(String email) {
		return usuarioDAO.buscarUsuarioPorEmail(email);
	}
	
	@Override
	public ResponseConsultarDTO<UsuarioTB> consultarUsusarioFiltros(RequestConsultarUsuariosDTO filtroUsuario) throws Exception{
		return usuarioDAO.consultarUsuariosPorFiltros(filtroUsuario);
	}

	@Override
	public Optional<UsuarioTB> loginUsuario(String user, String clave) {
		return usuarioDAO.loginUsuario(user,clave);
	}
	
	

	  

}

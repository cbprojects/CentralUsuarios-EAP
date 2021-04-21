package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IUsuarioDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuariosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;
import com.project.cafe.CentralUsuarios.service.IUsuarioService;
import com.project.cafe.CentralUsuarios.util.PasswordUtil;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDAO;

	@Transactional
	@Override
	public UsuarioTB crearUsuario(UsuarioTB usuario) throws Exception {
		usuario.setContrasena(PasswordUtil.encriptarAES(usuario.getContrasena(), "B13EC3B0742D2308"));
		return usuarioDAO.crearUsuario(usuario);
	}

	@Transactional
	@Override
	public UsuarioTB modificarUsuario(UsuarioTB usuario) {
		return usuarioDAO.modificarUsuario(usuario);
	}
	
	@Override
	public List<UsuarioTB> buscarUsuarioPorNick(String nickUsuario) {
		return usuarioDAO.buscarUsuarioPorNick(nickUsuario);
	}
	
	@Override
	public ResponseConsultarDTO<UsuarioTB> consultarUsusarioFiltros(RequestConsultarUsuariosDTO filtroUsuario) {
		return usuarioDAO.consultarUsuariosPorFiltros(filtroUsuario);
	}
	
	

	  

}

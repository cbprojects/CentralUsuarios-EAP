package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IUsuariosDao;
import com.project.cafe.CentralUsuarios.enums.EEstado;
import com.project.cafe.CentralUsuarios.model.UsuarioAutorTB;
import com.project.cafe.CentralUsuarios.service.IUsuariosService;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PasswordUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

	@Autowired
	private IUsuariosDao usuariosDAO;

	@Override
	public List<UsuarioAutorTB> consultarUsuarios() {
		return usuariosDAO.consultarUsuarios();
	}

	@Override
	public List<UsuarioAutorTB> consultarUsuariosPorFiltros(UsuarioAutorTB filtroUsuario) {
		return usuariosDAO.consultarUsuariosPorFiltros(filtroUsuario);
	}

	@Transactional
	@Override
	public UsuarioAutorTB crearUsuario(UsuarioAutorTB usuarioAutor) {
		String passwordEncrypt = Util.encriptarPassword(usuarioAutor.getPassword());
		usuarioAutor.setPassword(passwordEncrypt);
		return usuariosDAO.crearUsuario(usuarioAutor);
	}

	@Transactional
	@Override
	public UsuarioAutorTB modificarUsuario(UsuarioAutorTB usuarioAutor) {
		return usuariosDAO.modificarUsuario(usuarioAutor);
	}

	@Transactional
	@Override
	public UsuarioAutorTB loginUsuario(String usuario, String password) {
		UsuarioAutorTB usuarioLogueado = null;
		List<UsuarioAutorTB> listaUsuariosAutorTB = usuariosDAO.consultarUsuariosPorUsuario(usuario);
		if (listaUsuariosAutorTB != null && !listaUsuariosAutorTB.isEmpty()) {
			String salt = PasswordUtil.getSalt(ConstantesValidaciones.SALT_ENCRIPTAR_CLAVE, password);
			boolean passwordVerificada = PasswordUtil.verifyUserPassword(password,
					listaUsuariosAutorTB.get(0).getPassword(), salt);
			if (passwordVerificada && listaUsuariosAutorTB.get(0).getEstado() == EEstado.ACTIVO.ordinal()) {
				usuarioLogueado = listaUsuariosAutorTB.get(0);
			}
		}

		return usuarioLogueado;
	}
}

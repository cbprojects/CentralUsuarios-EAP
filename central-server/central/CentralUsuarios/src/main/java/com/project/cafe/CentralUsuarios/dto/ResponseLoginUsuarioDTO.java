package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;



public class ResponseLoginUsuarioDTO implements Serializable  {

	private static final long serialVersionUID = 1548524783737835053L;

	private UsuarioTB usuario;
	
	private List<RolTB> listaRoles;

	public UsuarioTB getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTB usuario) {
		this.usuario = usuario;
	}

	public List<RolTB> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<RolTB> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public ResponseLoginUsuarioDTO(UsuarioTB usuario, List<RolTB> listaRoles) {
		this.usuario = usuario;
		this.listaRoles = listaRoles;
	}

	public ResponseLoginUsuarioDTO() {
	}

}

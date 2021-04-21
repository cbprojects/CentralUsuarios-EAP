package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.UsuarioTB;

public class RequestConsultarUsuariosDTO implements Serializable {	

	private static final long serialVersionUID = 5006630610006189707L;

	private UsuarioTB usuario;

	private int registroInicial;

	private int cantidadRegistro;

	public UsuarioTB getusuario() {
		return usuario;
	}

	public void setRol(UsuarioTB usuario) {
		this.usuario = usuario;
	}

	public int getRegistroInicial() {
		return registroInicial;
	}

	public void setRegistroInicial(int registroInicial) {
		this.registroInicial = registroInicial;
	}

	public int getCantidadRegistro() {
		return cantidadRegistro;
	}

	public void setCantidadRegistro(int cantidadRegistro) {
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarUsuariosDTO(UsuarioTB usuario, int registroInicial, int cantidadRegistro) {
		super();
		this.usuario = usuario;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarUsuariosDTO() {

	}

}

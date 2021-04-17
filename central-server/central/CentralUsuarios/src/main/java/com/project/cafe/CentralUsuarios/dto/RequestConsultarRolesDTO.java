package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.RolTB;

public class RequestConsultarRolesDTO implements Serializable {

	private static final long serialVersionUID = 7398871854193481218L;

	private RolTB rol;

	private int registroInicial;

	private int cantidadRegistro;

	public RolTB getRol() {
		return rol;
	}

	public void setRol(RolTB rol) {
		this.rol = rol;
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

	public RequestConsultarRolesDTO(RolTB rol, int registroInicial, int cantidadRegistro) {
		super();
		this.rol = rol;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarRolesDTO() {

	}

}

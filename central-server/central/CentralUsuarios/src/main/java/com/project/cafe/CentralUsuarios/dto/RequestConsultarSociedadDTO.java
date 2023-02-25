package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.SociedadTB;

public class RequestConsultarSociedadDTO implements Serializable {

	private static final long serialVersionUID = -4796060226422710142L;

	private SociedadTB sociedad;

	private int registroInicial;

	private int cantidadRegistro;
				
	public SociedadTB getSociedad() {
		return sociedad;
	}

	public void setSociedad(SociedadTB sociedad) {
		this.sociedad = sociedad;
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

	public RequestConsultarSociedadDTO(SociedadTB sociedad, int registroInicial, int cantidadRegistro) {
		super();
		this.sociedad = sociedad;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarSociedadDTO() {

	}

}

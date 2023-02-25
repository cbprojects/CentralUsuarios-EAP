package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import com.project.cafe.CentralUsuarios.model.SociedadAreaTB;

public class RequestConsultarSociedadAreaDTO implements Serializable {

	private static final long serialVersionUID = 8629929548325670317L;

	private SociedadAreaTB sociedadArea;

	private int registroInicial;

	private int cantidadRegistro;

	public SociedadAreaTB getSociedadArea() {
		return sociedadArea;
	}

	public void setSociedadArea(SociedadAreaTB sociedadArea) {
		this.sociedadArea = sociedadArea;
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

	public RequestConsultarSociedadAreaDTO(SociedadAreaTB sociedadArea, int registroInicial, int cantidadRegistro) {
		super();
		this.sociedadArea = sociedadArea;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarSociedadAreaDTO() {

	}

}

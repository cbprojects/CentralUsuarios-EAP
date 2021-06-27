package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.CajaTB;

public class RequestConsultarCajasDTO implements Serializable {

	private static final long serialVersionUID = 6613459049077915901L;

	private CajaTB caja;

	private int registroInicial;

	private int cantidadRegistro;

	public CajaTB getCaja() {
		return caja;
	}

	public void setCaja(CajaTB caja) {
		this.caja = caja;
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

	public RequestConsultarCajasDTO(CajaTB caja, int registroInicial, int cantidadRegistro) {
		this.caja = caja;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarCajasDTO() {
	}
}

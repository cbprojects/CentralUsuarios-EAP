package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;

public class RequestConsultarUnidadDocumentalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4269388733663620222L;

	private UnidadDocumentalTB unidadDocumental;

	private int registroInicial;

	private int cantidadRegistro;

	public UnidadDocumentalTB getUnidadDocumental() {
		return unidadDocumental;
	}

	public void setUnidadDocumental(UnidadDocumentalTB unidadDocumental) {
		this.unidadDocumental = unidadDocumental;
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

	public RequestConsultarUnidadDocumentalDTO(UnidadDocumentalTB unidadDocumental, int registroInicial,
			int cantidadRegistro) {
		this.unidadDocumental = unidadDocumental;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarUnidadDocumentalDTO() {
	}
}
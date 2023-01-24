package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.RolTB;

public class RequestConsultarMasivoDTO implements Serializable {

	private static final long serialVersionUID = -6703637883793946112L;
	
	private Integer tipoMasivo;

	private MasivoDTO masivo;

	private int registroInicial;

	private int cantidadRegistro;

	public Integer getTipoMasivo() {
		return tipoMasivo;
	}

	public void setTipoMasivo(Integer tipoMasivo) {
		this.tipoMasivo = tipoMasivo;
	}

	public MasivoDTO getMasivo() {
		return masivo;
	}

	public void setMasivo(MasivoDTO masivo) {
		this.masivo = masivo;
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

	public RequestConsultarMasivoDTO(Integer tipoMasivo, MasivoDTO masivo, int registroInicial, int cantidadRegistro) {
		super();
		this.tipoMasivo = tipoMasivo;
		this.masivo = masivo;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarMasivoDTO() {
		super();
	}

	
}

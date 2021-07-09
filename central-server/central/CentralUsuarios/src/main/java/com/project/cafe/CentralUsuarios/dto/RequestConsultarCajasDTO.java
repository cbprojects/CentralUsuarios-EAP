package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.CajaTB;

public class RequestConsultarCajasDTO implements Serializable {

	private static final long serialVersionUID = 6613459049077915901L;

	private CajaTB caja;

	private int registroInicial;

	private int cantidadRegistro;

	private long idSede;

	private long idBodega;

	private long idBloque;

	private long idCuerpo;

	private long idEstante;

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

	public long getIdSede() {
		return idSede;
	}

	public void setIdSede(long idSede) {
		this.idSede = idSede;
	}

	public long getIdBodega() {
		return idBodega;
	}

	public void setIdBodega(long idBodega) {
		this.idBodega = idBodega;
	}

	public long getIdBloque() {
		return idBloque;
	}

	public void setIdBloque(long idBloque) {
		this.idBloque = idBloque;
	}

	public long getIdCuerpo() {
		return idCuerpo;
	}

	public void setIdCuerpo(long idCuerpo) {
		this.idCuerpo = idCuerpo;
	}

	public long getIdEstante() {
		return idEstante;
	}

	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
	}

	public RequestConsultarCajasDTO(CajaTB caja, int registroInicial, int cantidadRegistro, long idSede, long idBodega,
			long idBloque, long idCuerpo, long idEstante) {
		this.caja = caja;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
		this.idSede = idSede;
		this.idBodega = idBodega;
		this.idBloque = idBloque;
		this.idCuerpo = idCuerpo;
		this.idEstante = idEstante;
	}

	public RequestConsultarCajasDTO() {
	}
	
}

package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

public class RequestConsultarSociedadActivosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5958921633524385071L;
	private Long idCliente;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public RequestConsultarSociedadActivosDTO() {

	}

}

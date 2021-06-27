package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class RequestConsultarCuerposActivosDTO implements Serializable {

	private static final long serialVersionUID = -5804747549974441818L;
	private Long idBloque;

	public Long getIdBloque() {
		return idBloque;
	}



	public void setIdBloque(Long idBloque) {
		this.idBloque = idBloque;
	}



	public RequestConsultarCuerposActivosDTO() {

	}

}

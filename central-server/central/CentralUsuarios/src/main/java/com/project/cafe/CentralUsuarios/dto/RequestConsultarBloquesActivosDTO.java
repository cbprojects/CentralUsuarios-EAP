package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class RequestConsultarBloquesActivosDTO implements Serializable {

	private static final long serialVersionUID = 9023595680230406559L;
	
	private Long idBodega;

	public Long getIdBodega() {
		return idBodega;
	}

	public void setIdBodega(Long idBodega) {
		this.idBodega = idBodega;
	}

	public RequestConsultarBloquesActivosDTO() {

	}

}

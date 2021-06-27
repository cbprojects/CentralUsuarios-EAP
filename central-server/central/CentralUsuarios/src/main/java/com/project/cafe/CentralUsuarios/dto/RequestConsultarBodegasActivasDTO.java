package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class RequestConsultarBodegasActivasDTO implements Serializable {

	private static final long serialVersionUID = 8269832019842300982L;
	private Long idSede;


	public Long getIdSede() {
		return idSede;
	}


	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}


	public RequestConsultarBodegasActivasDTO() {

	}

}

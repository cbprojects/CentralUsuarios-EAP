package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class RequestConsultarEntrepanosActivosDTO implements Serializable {
	
	private static final long serialVersionUID = -2565595551648014029L;
	private Long idEstante;



	public Long getIdEstante() {
		return idEstante;
	}



	public void setIdEstante(Long idEstante) {
		this.idEstante = idEstante;
	}



	public RequestConsultarEntrepanosActivosDTO() {

	}

}

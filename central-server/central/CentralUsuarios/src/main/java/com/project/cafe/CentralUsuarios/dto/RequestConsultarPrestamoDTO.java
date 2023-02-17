package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

public class RequestConsultarPrestamoDTO implements Serializable {

	private static final long serialVersionUID = -4084177186127314319L;

	private Long idUd;
	
	public Long getIdUd() {
		return idUd;
	}



	public void setIdUd(Long idUd) {
		this.idUd = idUd;
	}



	public RequestConsultarPrestamoDTO(Long idUd) {
		super();
		this.idUd = idUd;
	}



	public RequestConsultarPrestamoDTO() {

	}

}

package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class RequestConsultarEstantesActivosDTO implements Serializable {
	
	private static final long serialVersionUID = -2565595551648014029L;
	private Long idCuerpo;



	public Long getIdCuerpo() {
		return idCuerpo;
	}



	public void setIdCuerpo(Long idCuerpo) {
		this.idCuerpo = idCuerpo;
	}



	public RequestConsultarEstantesActivosDTO() {

	}

}

package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class RequestConsultarSedesActivasDTO implements Serializable {

	private static final long serialVersionUID = 5091862137635507426L;

	private String email;

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public RequestConsultarSedesActivasDTO() {

	}

}

package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class RequestConsultarPorIDDTO implements Serializable {	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3815409479213151607L;
	
	
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RequestConsultarPorIDDTO() {

	}

}

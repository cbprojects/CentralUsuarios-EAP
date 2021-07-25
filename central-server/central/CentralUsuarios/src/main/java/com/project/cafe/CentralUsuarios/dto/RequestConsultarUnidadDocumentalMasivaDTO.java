package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class RequestConsultarUnidadDocumentalMasivaDTO implements Serializable {	
	
	private static final long serialVersionUID = -1817608099158323891L;
	/**
	 * 
	 */
	
	private Long idCajaUno;
	private Long idCajaDos;
	
	

	public Long getIdCajaUno() {
		return idCajaUno;
	}



	public void setIdCajaUno(Long idCajaUno) {
		this.idCajaUno = idCajaUno;
	}



	public Long getIdCajaDos() {
		return idCajaDos;
	}



	public void setIdCajaDos(Long idCajaDos) {
		this.idCajaDos = idCajaDos;
	}



	public RequestConsultarUnidadDocumentalMasivaDTO() {

	}

}

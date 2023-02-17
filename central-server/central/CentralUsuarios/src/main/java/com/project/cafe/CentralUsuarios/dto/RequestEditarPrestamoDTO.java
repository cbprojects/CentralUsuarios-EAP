package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

public class RequestEditarPrestamoDTO implements Serializable {

	private static final long serialVersionUID = 8384301299106748160L;

	private Boolean esCrear;
	
	private Long idUd;
	
	private String responsable;
	
	private String observacion;
	
	private String usuarioCreacion;
	
	public Boolean getEsCrear() {
		return esCrear;
	}

	public void setEsCrear(Boolean esCrear) {
		this.esCrear = esCrear;
	}

	public Long getIdUd() {
		return idUd;
	}


	public void setIdUd(Long idUd) {
		this.idUd = idUd;
	}



	public String getResponsable() {
		return responsable;
	}



	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}



	public String getObservacion() {
		return observacion;
	}



	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	



	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}



	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}



	public RequestEditarPrestamoDTO(Boolean esCrear, Long idUd, String responsable, String observacion,
			String usuarioCreacion) {
		super();
		this.esCrear = esCrear;
		this.idUd = idUd;
		this.responsable = responsable;
		this.observacion = observacion;
		this.usuarioCreacion = usuarioCreacion;
	}



	public RequestEditarPrestamoDTO() {

	}

}

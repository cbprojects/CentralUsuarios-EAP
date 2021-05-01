package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;



public class ResponseCrearRolPerfilDTO  implements Serializable  {

	private static final long serialVersionUID = 6709729036198359688L;

	private String codigo;

	private String mensaje;

	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public ResponseCrearRolPerfilDTO() {
		
	}

	public ResponseCrearRolPerfilDTO(String codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	

}

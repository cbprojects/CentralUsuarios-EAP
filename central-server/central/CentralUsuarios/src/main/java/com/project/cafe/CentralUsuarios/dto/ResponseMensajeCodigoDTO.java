package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;



public class ResponseMensajeCodigoDTO  implements Serializable  {

	private static final long serialVersionUID = -3044634764063014328L;

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

	public ResponseMensajeCodigoDTO() {
		
	}

	public ResponseMensajeCodigoDTO(String codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	

}

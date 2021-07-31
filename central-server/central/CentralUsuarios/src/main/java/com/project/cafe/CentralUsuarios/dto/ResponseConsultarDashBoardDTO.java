package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class ResponseConsultarDashBoardDTO implements Serializable {

	private static final long serialVersionUID = -4297437360325755988L;
	private Long idUsuario;
	private Long idPerfil;
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public ResponseConsultarDashBoardDTO() {

	}

	public ResponseConsultarDashBoardDTO(Long idUsuario, Long idPerfil) {
		this.idUsuario = idUsuario;
		this.idPerfil = idPerfil;
	}
	
}

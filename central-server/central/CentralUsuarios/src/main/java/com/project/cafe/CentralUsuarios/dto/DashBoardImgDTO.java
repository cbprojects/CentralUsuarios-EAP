package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class DashBoardImgDTO implements Serializable {

	private static final long serialVersionUID = -1485131317977637589L;
	private String rutaImg;
	private String estado;
	
	public DashBoardImgDTO(String rutaImg,String estado) {
		this.rutaImg = rutaImg;
		this.estado = estado;
	}

	public DashBoardImgDTO() {

	}

	public String getRutaImg() {
		return rutaImg;
	}

	public void setRutaImg(String rutaImg) {
		this.rutaImg = rutaImg;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

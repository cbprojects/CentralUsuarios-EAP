package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.BaseEntidadTB;

//@XmlRootElement
public class MasivoDTO extends BaseEntidadTB implements Serializable {

	
	private static final long serialVersionUID = 5755029091905695439L;
	
	public Long idMasivo;
	public String nombre1;
	public String nombre2;
	
	
	public Long getIdMasivo() {
		return idMasivo;
	}
	public void setIdMasivo(Long idMasivo) {
		this.idMasivo = idMasivo;
	}
	public String getNombre1() {
		return nombre1;
	}
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	
	
}

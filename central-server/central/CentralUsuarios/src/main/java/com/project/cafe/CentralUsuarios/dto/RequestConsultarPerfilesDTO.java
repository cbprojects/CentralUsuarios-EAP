package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.PerfilTB;


public class RequestConsultarPerfilesDTO  implements Serializable  {

	
	private static final long serialVersionUID = -1547362098210762545L;

	private PerfilTB entidad;

	private int registroInicial;

	private int cantidadRegistro;

	public PerfilTB getEntidad() {
		return entidad;
	}

	public void setEntidad(PerfilTB entidad) {
		this.entidad = entidad;
	}

	public int getRegistroInicial() {
		return registroInicial;
	}

	public void setRegistroInicial(int registroInicial) {
		this.registroInicial = registroInicial;
	}

	public int getCantidadRegistro() {
		return cantidadRegistro;
	}

	public void setCantidadRegistro(int cantidadRegistro) {
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarPerfilesDTO(PerfilTB entidad, int registroInicial, int cantidadRegistro) {
		this.entidad = entidad;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarPerfilesDTO() {
		
	}

	

}

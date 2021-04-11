package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.PerfilTB;


public class RequestConsultarPerfilesDTO  implements Serializable  {

	
	private static final long serialVersionUID = -1547362098210762545L;

	private PerfilTB perfil;

	private int registroInicial;

	private int cantidadRegistro;


	public PerfilTB getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilTB perfil) {
		this.perfil = perfil;
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

	public RequestConsultarPerfilesDTO(PerfilTB perfil, int registroInicial, int cantidadRegistro) {
		this.perfil = perfil;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarPerfilesDTO() {
		
	}

	

}

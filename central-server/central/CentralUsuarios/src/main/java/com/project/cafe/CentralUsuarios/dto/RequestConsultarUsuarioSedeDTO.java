package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import com.project.cafe.CentralUsuarios.model.UsuarioSedeTB;

public class RequestConsultarUsuarioSedeDTO implements Serializable {

	private static final long serialVersionUID = -1577988197742485248L;

	private UsuarioSedeTB usuarioSede;

	private int registroInicial;

	private int cantidadRegistro;
	
	

	public UsuarioSedeTB getUsuarioSede() {
		return usuarioSede;
	}

	public void setUsuarioSede(UsuarioSedeTB usuarioSede) {
		this.usuarioSede = usuarioSede;
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

	public RequestConsultarUsuarioSedeDTO(UsuarioSedeTB usuarioSede, int registroInicial, int cantidadRegistro) {
		super();
		this.usuarioSede = usuarioSede;
		this.registroInicial = registroInicial;
		this.cantidadRegistro = cantidadRegistro;
	}

	public RequestConsultarUsuarioSedeDTO() {

	}

}

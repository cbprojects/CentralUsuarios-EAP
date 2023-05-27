package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.UsuarioClienteTB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestConsultarUsuarioClienteDTO implements Serializable {

	
	private static final long serialVersionUID = 5162361874925361677L;

	private UsuarioClienteTB usuarioCliente;

	private int registroInicial;

	private int cantidadRegistro;
	
	

}

package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.model.SociedadTB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestConsultarUDRecepcionDTO implements Serializable {
	
	private static final long serialVersionUID = 6984856322251675930L;

	private ClienteTB cliente;
	
	private Long idUser;

	private int registroInicial;

	private int cantidadRegistro;

}
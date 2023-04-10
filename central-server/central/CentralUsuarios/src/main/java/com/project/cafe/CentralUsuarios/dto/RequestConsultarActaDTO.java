package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestConsultarActaDTO implements Serializable {
	
	private static final long serialVersionUID = -3668781276204018912L;

	private Long idUsuario;
	
	private Long tipoAprobado;
	
	private Boolean esAdmin;

	private int registroInicial;

	private int cantidadRegistro;

}
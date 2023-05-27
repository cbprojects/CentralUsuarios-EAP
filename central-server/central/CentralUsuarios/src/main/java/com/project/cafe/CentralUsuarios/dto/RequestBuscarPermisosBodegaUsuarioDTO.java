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
public class RequestBuscarPermisosBodegaUsuarioDTO implements Serializable {

	private static final long serialVersionUID = -431477879865641098L;

	private Long idBodega;

	private Long idUsuario;

}
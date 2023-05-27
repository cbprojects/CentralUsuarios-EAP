package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.PermisosBodegaTB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestConsultarPermisosBodegaDTO implements Serializable {

	private static final long serialVersionUID = 6737094008567366760L;

	private PermisosBodegaTB permisosBodega;

	private int registroInicial;

	private int cantidadRegistro;

	
}
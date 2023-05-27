package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.model.ProyectoTB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestConsultarProyectosDTO implements Serializable {

	private static final long serialVersionUID = -8687674808144542439L;

	private ProyectoTB proyecto;

	private int registroInicial;

	private int cantidadRegistro;

	
}
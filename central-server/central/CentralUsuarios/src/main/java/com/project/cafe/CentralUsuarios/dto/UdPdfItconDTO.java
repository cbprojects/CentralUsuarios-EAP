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
public class UdPdfItconDTO implements Serializable {
	
	private static final long serialVersionUID = -4345275829446918877L;

	private String caja;

	private String nombre;

	private String tipo;
	
	private String contenedor;
	
	private String fechaIni;
	
	private String fechaFin;
	
	private String conIni;
	
	private String conFin;

}
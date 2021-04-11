package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;



public class ResponseConsultarDTO <T extends Object> implements Serializable  {

	private static final long serialVersionUID = -1476097182138537349L;

	private List<T> resultado;

	private Long registrosTotales;

	public List<T> getResultado() {
		return resultado;
	}

	public void setResultado(List<T> resultado) {
		this.resultado = resultado;
	}

	public Long getRegistrosTotales() {
		return registrosTotales;
	}

	public void setRegistrosTotales(Long registrosTotales) {
		this.registrosTotales = registrosTotales;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ResponseConsultarDTO(List<T> resultado, Long registrosTotales) {
		this.resultado = resultado;
		this.registrosTotales = registrosTotales;
	}

	public ResponseConsultarDTO() {
		
	}

}

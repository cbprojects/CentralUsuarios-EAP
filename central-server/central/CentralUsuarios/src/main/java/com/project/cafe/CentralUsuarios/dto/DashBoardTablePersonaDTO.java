package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;


public class DashBoardTablePersonaDTO implements Serializable {

	private static final long serialVersionUID = 234001199680768765L;
	private List<String> cabeceras;
	private List<DashBoardPersonaDTO> values;
	private String estado;
	
	public DashBoardTablePersonaDTO(List<String> cabeceras, List<DashBoardPersonaDTO> values, String estado) {
		this.cabeceras = cabeceras;
		this.values = values;
		this.estado = estado;
	}

	public DashBoardTablePersonaDTO() {
	
	}

	public List<String> getCabeceras() {
		return cabeceras;
	}

	public void setCabeceras(List<String> cabeceras) {
		this.cabeceras = cabeceras;
	}

	public List<DashBoardPersonaDTO> getValues() {
		return values;
	}

	public void setValues(List<DashBoardPersonaDTO> values) {
		this.values = values;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;


public class DashBoardTableFacturaDTO implements Serializable {

	private static final long serialVersionUID = 2210461900425760151L;
	private List<String> cabeceras;
	private List<DashBoardFacturaDTO> values;
	private String estado;
	
	public DashBoardTableFacturaDTO(List<String> cabeceras, List<DashBoardFacturaDTO> values, String estado) {
		this.cabeceras = cabeceras;
		this.values = values;
		this.estado = estado;
	}

	public DashBoardTableFacturaDTO() {
	
	}

	public List<String> getCabeceras() {
		return cabeceras;
	}

	public void setCabeceras(List<String> cabeceras) {
		this.cabeceras = cabeceras;
	}

	public List<DashBoardFacturaDTO> getValues() {
		return values;
	}

	public void setValues(List<DashBoardFacturaDTO> values) {
		this.values = values;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

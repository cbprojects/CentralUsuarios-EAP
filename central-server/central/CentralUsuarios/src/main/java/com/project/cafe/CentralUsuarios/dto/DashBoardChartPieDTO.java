package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;


public class DashBoardChartPieDTO implements Serializable {

	private static final long serialVersionUID = -4450508609459957269L;
	private List<DashBoardChartDTO> data;
	private String estado;
	
	public DashBoardChartPieDTO() {
	
	}

	public DashBoardChartPieDTO(List<DashBoardChartDTO> data, String estado) {
		this.data = data;
		this.estado = estado;
	}

	public List<DashBoardChartDTO> getData() {
		return data;
	}

	public void setData(List<DashBoardChartDTO> data) {
		this.data = data;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

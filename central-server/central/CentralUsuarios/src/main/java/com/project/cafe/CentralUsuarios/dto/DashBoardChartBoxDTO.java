package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class DashBoardChartBoxDTO implements Serializable {

	private static final long serialVersionUID = 4675493820892508858L;
	private DashBoardChartDTO data;
	private DashBoardBoxDTO box;
	private String estado;

	public DashBoardChartBoxDTO(DashBoardChartDTO data, DashBoardBoxDTO box, String estado) {
		this.data = data;
		this.box = box;
		this.estado = estado;
	}

	public DashBoardChartBoxDTO() {

	}

	public DashBoardChartDTO getData() {
		return data;
	}

	public void setData(DashBoardChartDTO data) {
		this.data = data;
	}

	public DashBoardBoxDTO getBox() {
		return box;
	}

	public void setBox(DashBoardBoxDTO box) {
		this.box = box;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}

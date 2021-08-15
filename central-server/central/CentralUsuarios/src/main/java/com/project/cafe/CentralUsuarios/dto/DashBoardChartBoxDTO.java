package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;


public class DashBoardChartBoxDTO implements Serializable {

	private static final long serialVersionUID = 4675493820892508858L;
	private List<DashBoardChartDTO> data;
	private DashBoardBoxDTO box;
	private String estado;

	public DashBoardChartBoxDTO(List<DashBoardChartDTO> data, DashBoardBoxDTO box, String estado) {
		this.data = data;
		this.box = box;
		this.estado = estado;
	}

	public DashBoardChartBoxDTO() {

	}

	public List<DashBoardChartDTO> getData() {
		return data;
	}

	public void setData(List<DashBoardChartDTO> data) {
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

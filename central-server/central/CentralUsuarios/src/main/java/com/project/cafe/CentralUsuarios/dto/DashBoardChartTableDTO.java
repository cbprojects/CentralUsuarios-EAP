package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;


public class DashBoardChartTableDTO implements Serializable {

	private static final long serialVersionUID = 1315417014397811413L;
	private List<DashBoardChartDTO> data;
	private DashBoardTablePersonaDTO table;
	private String estado;

	public DashBoardChartTableDTO(List<DashBoardChartDTO>  data, DashBoardTablePersonaDTO table, String estado) {
		this.data = data;
		this.table = table;
		this.estado = estado;
	}

	public DashBoardChartTableDTO() {

	}

	public List<DashBoardChartDTO>  getData() {
		return data;
	}

	public void setData(List<DashBoardChartDTO>  data) {
		this.data = data;
	}

	public DashBoardTablePersonaDTO getTable() {
		return table;
	}

	public void setTable(DashBoardTablePersonaDTO table) {
		this.table = table;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}

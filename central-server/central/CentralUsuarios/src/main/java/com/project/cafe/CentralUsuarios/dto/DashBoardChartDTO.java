package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;


public class DashBoardChartDTO implements Serializable {

	private static final long serialVersionUID = 5815571462998322025L;
	private String color;
	private List<String> columns;
	private String label;
	private String type;
	
	public DashBoardChartDTO(String color, List<String> columns, String label, String type) {
		this.color = color;
		this.columns = columns;
		this.label = label;
		this.type = type;
	}

	public DashBoardChartDTO() {

	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

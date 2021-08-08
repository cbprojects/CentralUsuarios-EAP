package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;


public class DashBoardChartDTO implements Serializable {

	private static final long serialVersionUID = 5815571462998322025L;
	
	private String column;
	private String value;
	private String label;
	private String type;
	private String color;
	
	public DashBoardChartDTO(String color, String column, String label, String type) {
		this.color = color;
		this.column = column;
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

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	

}

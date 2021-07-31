package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class DashBoardBoxDTO implements Serializable {

	private static final long serialVersionUID = -1485131317977637589L;
	private String color;
	private String principalLabel;
	private String principalValue;
	private String subtitleLabel;
	private String subtitleValue;
	private String upperLabel;
	private String upperValue;
	private String estado;
	
	public DashBoardBoxDTO(String color, String principalLabel, String principalValue, String subtitleLabel,
			String subtitleValue, String upperLabel, String upperValue, String estado) {
		this.color = color;
		this.principalLabel = principalLabel;
		this.principalValue = principalValue;
		this.subtitleLabel = subtitleLabel;
		this.subtitleValue = subtitleValue;
		this.upperLabel = upperLabel;
		this.upperValue = upperValue;
		this.estado = estado;
	}

	public DashBoardBoxDTO() {

	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrincipalLabel() {
		return principalLabel;
	}

	public void setPrincipalLabel(String principalLabel) {
		this.principalLabel = principalLabel;
	}

	public String getPrincipalValue() {
		return principalValue;
	}

	public void setPrincipalValue(String principalValue) {
		this.principalValue = principalValue;
	}

	public String getSubtitleLabel() {
		return subtitleLabel;
	}

	public void setSubtitleLabel(String subtitleLabel) {
		this.subtitleLabel = subtitleLabel;
	}

	public String getSubtitleValue() {
		return subtitleValue;
	}

	public void setSubtitleValue(String subtitleValue) {
		this.subtitleValue = subtitleValue;
	}

	public String getUpperLabel() {
		return upperLabel;
	}

	public void setUpperLabel(String upperLabel) {
		this.upperLabel = upperLabel;
	}

	public String getUpperValue() {
		return upperValue;
	}

	public void setUpperValue(String upperValue) {
		this.upperValue = upperValue;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

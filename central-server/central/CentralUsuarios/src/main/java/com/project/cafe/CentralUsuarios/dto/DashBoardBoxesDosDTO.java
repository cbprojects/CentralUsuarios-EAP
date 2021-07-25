package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class DashBoardBoxesDosDTO implements Serializable {

	private static final long serialVersionUID = -5696448243771124575L;
	private DashBoardBoxDTO box1;
	private DashBoardBoxDTO box2;
	
	public DashBoardBoxesDosDTO(DashBoardBoxDTO box1, DashBoardBoxDTO box2) {
		this.box1 = box1;
		this.box2 = box2;
		
	}

	public DashBoardBoxesDosDTO() {

	}

	public DashBoardBoxDTO getBox1() {
		return box1;
	}

	public void setBox1(DashBoardBoxDTO box1) {
		this.box1 = box1;
	}

	public DashBoardBoxDTO getBox2() {
		return box2;
	}

	public void setBox2(DashBoardBoxDTO box2) {
		this.box2 = box2;
	}
	
}

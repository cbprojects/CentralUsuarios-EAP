package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class DashBoardBoxesTresDTO implements Serializable {

	private static final long serialVersionUID = -4620273216045889012L;
	private DashBoardBoxDTO box1;
	private DashBoardBoxDTO box2;
	private DashBoardBoxDTO box3;
	private DashBoardBoxDTO box4;
	
	public DashBoardBoxesTresDTO(DashBoardBoxDTO box1, DashBoardBoxDTO box2, DashBoardBoxDTO box3, DashBoardBoxDTO box4) {
		this.box1 = box1;
		this.box2 = box2;
		this.box3 = box3;
		this.box4 = box4;
	}

	public DashBoardBoxesTresDTO() {

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

	public DashBoardBoxDTO getBox3() {
		return box3;
	}

	public void setBox3(DashBoardBoxDTO box3) {
		this.box3 = box3;
	}

	public DashBoardBoxDTO getBox4() {
		return box4;
	}

	public void setBox4(DashBoardBoxDTO box4) {
		this.box4 = box4;
	}

}

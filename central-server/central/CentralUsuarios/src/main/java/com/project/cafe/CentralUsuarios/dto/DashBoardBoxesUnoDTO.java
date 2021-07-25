package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class DashBoardBoxesUnoDTO implements Serializable {

	private static final long serialVersionUID = -4620273216045889012L;
	private DashBoardBoxDTO box1;
	private DashBoardBoxDTO box2;
	private DashBoardBoxDTO box3;
	private DashBoardBoxDTO box4;
	private DashBoardBoxDTO box5;
	private DashBoardBoxDTO box6;

	public DashBoardBoxesUnoDTO(DashBoardBoxDTO box1, DashBoardBoxDTO box2, DashBoardBoxDTO box3, DashBoardBoxDTO box4,
			DashBoardBoxDTO box5, DashBoardBoxDTO box6) {
		this.box1 = box1;
		this.box2 = box2;
		this.box3 = box3;
		this.box4 = box4;
		this.box5 = box5;
		this.box6 = box6;
	}

	public DashBoardBoxesUnoDTO() {

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

	public DashBoardBoxDTO getBox5() {
		return box5;
	}

	public void setBox5(DashBoardBoxDTO box5) {
		this.box5 = box5;
	}

	public DashBoardBoxDTO getBox6() {
		return box6;
	}

	public void setBox6(DashBoardBoxDTO box6) {
		this.box6 = box6;
	}

}

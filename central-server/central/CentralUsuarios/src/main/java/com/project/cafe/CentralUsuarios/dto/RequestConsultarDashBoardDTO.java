package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class RequestConsultarDashBoardDTO implements Serializable {

	private static final long serialVersionUID = -8503006856969884989L;
	private DashBoardBoxesUnoDTO boxes1;
	private DashBoardBoxesDosDTO boxes2;
	private DashBoardBoxesTresDTO boxes3;
	private DashBoardChartBoxDTO chartBox1;
	private DashBoardChartBoxDTO chartBox2;
	private DashBoardChartBoxDTO chartBox3;
	private DashBoardChartBoxDTO chartBox4;
	private DashBoardChartPieDTO chartPie1;
	private DashBoardChartPieDTO chartPie2;
	private DashBoardChartTableDTO chartTable1;
	private DashBoardTablePersonaDTO table1;
	private DashBoardTablePersonaDTO table2;
	private DashBoardTablePersonaDTO table3;
	private DashBoardTablePersonaDTO table4;
	private DashBoardTableFacturaDTO table5;
	private DashBoardImgDTO img;
	
	public RequestConsultarDashBoardDTO(DashBoardBoxesUnoDTO boxes1, DashBoardBoxesDosDTO boxes2,
			DashBoardBoxesTresDTO boxes3, DashBoardChartBoxDTO chartBox1, DashBoardChartBoxDTO chartBox2,
			DashBoardChartBoxDTO chartBox3, DashBoardChartBoxDTO chartBox4, DashBoardChartPieDTO chartPie1,
			DashBoardChartPieDTO chartPie2, DashBoardChartTableDTO chartTable1, DashBoardTablePersonaDTO table1,
			DashBoardTablePersonaDTO table2, DashBoardTablePersonaDTO table3, DashBoardTablePersonaDTO table4,
			DashBoardTableFacturaDTO table5,DashBoardImgDTO img) {
		this.boxes1 = boxes1;
		this.boxes2 = boxes2;
		this.boxes3 = boxes3;
		this.chartBox1 = chartBox1;
		this.chartBox2 = chartBox2;
		this.chartBox3 = chartBox3;
		this.chartBox4 = chartBox4;
		this.chartPie1 = chartPie1;
		this.chartPie2 = chartPie2;
		this.chartTable1 = chartTable1;
		this.table1 = table1;
		this.table2 = table2;
		this.table3 = table3;
		this.table4 = table4;
		this.table5 = table5;
		this.img = img;
	}

	public RequestConsultarDashBoardDTO() {
	
	}

	public DashBoardBoxesUnoDTO getBoxes1() {
		return boxes1;
	}

	public void setBoxes1(DashBoardBoxesUnoDTO boxes1) {
		this.boxes1 = boxes1;
	}

	public DashBoardBoxesDosDTO getBoxes2() {
		return boxes2;
	}

	public void setBoxes2(DashBoardBoxesDosDTO boxes2) {
		this.boxes2 = boxes2;
	}

	public DashBoardBoxesTresDTO getBoxes3() {
		return boxes3;
	}

	public void setBoxes3(DashBoardBoxesTresDTO boxes3) {
		this.boxes3 = boxes3;
	}

	public DashBoardChartBoxDTO getChartBox1() {
		return chartBox1;
	}

	public void setChartBox1(DashBoardChartBoxDTO chartBox1) {
		this.chartBox1 = chartBox1;
	}

	public DashBoardChartBoxDTO getChartBox2() {
		return chartBox2;
	}

	public void setChartBox2(DashBoardChartBoxDTO chartBox2) {
		this.chartBox2 = chartBox2;
	}

	public DashBoardChartBoxDTO getChartBox3() {
		return chartBox3;
	}

	public void setChartBox3(DashBoardChartBoxDTO chartBox3) {
		this.chartBox3 = chartBox3;
	}

	public DashBoardChartBoxDTO getChartBox4() {
		return chartBox4;
	}

	public void setChartBox4(DashBoardChartBoxDTO chartBox4) {
		this.chartBox4 = chartBox4;
	}

	public DashBoardChartPieDTO getChartPie1() {
		return chartPie1;
	}

	public void setChartPie1(DashBoardChartPieDTO chartPie1) {
		this.chartPie1 = chartPie1;
	}

	public DashBoardChartPieDTO getChartPie2() {
		return chartPie2;
	}

	public void setChartPie2(DashBoardChartPieDTO chartPie2) {
		this.chartPie2 = chartPie2;
	}

	public DashBoardChartTableDTO getChartTable1() {
		return chartTable1;
	}

	public void setChartTable1(DashBoardChartTableDTO chartTable1) {
		this.chartTable1 = chartTable1;
	}

	public DashBoardTablePersonaDTO getTable1() {
		return table1;
	}

	public void setTable1(DashBoardTablePersonaDTO table1) {
		this.table1 = table1;
	}

	public DashBoardTablePersonaDTO getTable2() {
		return table2;
	}

	public void setTable2(DashBoardTablePersonaDTO table2) {
		this.table2 = table2;
	}

	public DashBoardTablePersonaDTO getTable3() {
		return table3;
	}

	public void setTable3(DashBoardTablePersonaDTO table3) {
		this.table3 = table3;
	}

	public DashBoardTablePersonaDTO getTable4() {
		return table4;
	}

	public void setTable4(DashBoardTablePersonaDTO table4) {
		this.table4 = table4;
	}

	public DashBoardTableFacturaDTO getTable5() {
		return table5;
	}

	public void setTable5(DashBoardTableFacturaDTO table5) {
		this.table5 = table5;
	}

	public DashBoardImgDTO getImg() {
		return img;
	}

	public void setImg(DashBoardImgDTO img) {
		this.img = img;
	}
	
	
}

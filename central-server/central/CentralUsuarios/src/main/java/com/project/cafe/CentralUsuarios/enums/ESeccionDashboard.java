package com.project.cafe.CentralUsuarios.enums;

import java.util.ArrayList;
import java.util.List;

public enum ESeccionDashboard {
	VACIO("VACIO"),
	BOXES1("BOXES1"),
	BOXES2("BOXES2"),
	BOXES3("BOXES3"),
	CHARTBOX1("CHARTBOX1"),
	CHARTBOX2("CHARTBOX2"),
	CHARTBOX3("CHARTBOX3"),
	CHARTBOX4("CHARTBOX4"),
	CHARTPIE1("CHARTPIE1"),
	CHARTPIE2("CHARTPIE2"),
	CHARTTABLE1("CHARTTABLE1"),
	TABLE1("TABLE1"),
	TABLE2("TABLE2"),
	TABLE3("TABLE3"),
	TABLE4("TABLE4"),
	TABLE5("TABLE5");

	private final String nombre;

	private ESeccionDashboard(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	public static List<ESeccionDashboard> getListaSeccion(){
		List<ESeccionDashboard> lista = new ArrayList<ESeccionDashboard>();
		lista.add(BOXES1);
		lista.add(BOXES2);
		lista.add(BOXES3);
		lista.add(CHARTBOX1);
		lista.add(CHARTBOX2);
		lista.add(CHARTBOX3);
		lista.add(CHARTBOX4);
		lista.add(CHARTPIE1);
		lista.add(CHARTPIE2);
		lista.add(CHARTTABLE1);
		lista.add(TABLE1);
		lista.add(TABLE2);
		lista.add(TABLE3);
		lista.add(TABLE4);
		lista.add(TABLE5);
		return lista;
	}

}

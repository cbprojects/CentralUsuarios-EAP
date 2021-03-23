package com.project.cafe.CentralUsuarios.enums;

public enum ESiNo {
	NO("NO"), SI("SI");

	private final String nombre;

	private ESiNo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}

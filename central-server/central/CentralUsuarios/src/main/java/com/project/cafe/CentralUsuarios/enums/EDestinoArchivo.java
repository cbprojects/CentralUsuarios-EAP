package com.project.cafe.CentralUsuarios.enums;

public enum EDestinoArchivo {
	USER("USER");

	private final String nombre;

	private EDestinoArchivo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}

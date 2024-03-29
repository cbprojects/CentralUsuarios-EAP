package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

import com.project.cafe.CentralUsuarios.enums.ETipoDirectorio;

public class DirectorioDTO implements Serializable {

	private static final long serialVersionUID = -8289436565547634401L;

	private String ruta;

	private String nombre;

	private ETipoDirectorio tipoDirectorio;

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ETipoDirectorio getTipoDirectorio() {
		return tipoDirectorio;
	}

	public void setTipoDirectorio(ETipoDirectorio tipoDirectorio) {
		this.tipoDirectorio = tipoDirectorio;
	}

}

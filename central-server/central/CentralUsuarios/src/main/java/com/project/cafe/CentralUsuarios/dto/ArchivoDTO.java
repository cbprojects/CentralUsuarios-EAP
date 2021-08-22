package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

//@XmlRootElement
public class ArchivoDTO implements Serializable {
	private static final long serialVersionUID = -93749543131258839L;

	String nombreArchivo;
	byte[] archivo;
	

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	

}

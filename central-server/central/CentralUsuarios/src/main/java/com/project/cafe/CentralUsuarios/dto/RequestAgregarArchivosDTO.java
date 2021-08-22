package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

//@XmlRootElement
public class RequestAgregarArchivosDTO implements Serializable {

	
	private static final long serialVersionUID = 6513410310302258762L;
	
	public List<ArchivoDTO> listaArchivosPorSubir;
	public long idUnidadDocumental;
	
	public List<ArchivoDTO> getListaArchivosPorSubir() {
		return listaArchivosPorSubir;
	}
	public void setListaArchivosPorSubir(List<ArchivoDTO> listaArchivosPorSubir) {
		this.listaArchivosPorSubir = listaArchivosPorSubir;
	}
	public long getIdUnidadDocumental() {
		return idUnidadDocumental;
	}
	public void setIdUnidadDocumental(long idUnidadDocumental) {
		this.idUnidadDocumental = idUnidadDocumental;
	}
	
	
}

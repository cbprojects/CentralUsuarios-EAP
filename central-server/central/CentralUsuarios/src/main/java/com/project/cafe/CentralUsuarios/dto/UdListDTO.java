package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

//@XmlRootElement
public class UdListDTO implements Serializable {
	
	private static final long serialVersionUID = 1377669280563051486L;
	
	public Long idUd;
	public String codigoUd;
	public List<String> documentosUd;
	
	public Long getIdUd() {
		return idUd;
	}
	public void setIdUd(Long idUd) {
		this.idUd = idUd;
	}
	public String getCodigoUd() {
		return codigoUd;
	}
	public void setCodigoUd(String codigoUd) {
		this.codigoUd = codigoUd;
	}
	public List<String> getDocumentosUd() {
		return documentosUd;
	}
	public void setDocumentosUd(List<String> documentosUd) {
		this.documentosUd = documentosUd;
	}
	public UdListDTO(Long idUd, String codigoUd, List<String> documentosUd) {
		super();
		this.idUd = idUd;
		this.codigoUd = codigoUd;
		this.documentosUd = documentosUd;
	}
	public UdListDTO() {
		
	}
	
	

	
	

}

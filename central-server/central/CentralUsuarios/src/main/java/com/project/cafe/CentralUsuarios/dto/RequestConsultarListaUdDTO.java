package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

//@XmlRootElement
public class RequestConsultarListaUdDTO implements Serializable {

	
	private static final long serialVersionUID = -722786711693692532L;
	
	public Long idCliente;
	public Long idSociedad;
	public Long idCaja;
	public Long idUnidadDocumental;
	public Long idTipoUD;
	public Long idArea;
	public Long idContenedor;
	
	
	public Long getIdSociedad() {
		return idSociedad;
	}
	public void setIdSociedad(Long idSociedad) {
		this.idSociedad = idSociedad;
	}
	public Long getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(Long idCaja) {
		this.idCaja = idCaja;
	}
	public Long getIdUnidadDocumental() {
		return idUnidadDocumental;
	}
	public void setIdUnidadDocumental(Long idUnidadDocumental) {
		this.idUnidadDocumental = idUnidadDocumental;
	}
	public Long getIdTipoUD() {
		return idTipoUD;
	}
	public void setIdTipoUD(Long idTipoUD) {
		this.idTipoUD = idTipoUD;
	}
	public Long getIdArea() {
		return idArea;
	}
	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}
	public Long getIdContenedor() {
		return idContenedor;
	}
	public void setIdContenedor(Long idContenedor) {
		this.idContenedor = idContenedor;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public RequestConsultarListaUdDTO() {
		
	}
	public RequestConsultarListaUdDTO(Long idCliente, Long idSociedad, Long idCaja, Long idUnidadDocumental,
			Long idTipoUD, Long idArea, Long idContenedor) {
		super();
		this.idCliente = idCliente;
		this.idSociedad = idSociedad;
		this.idCaja = idCaja;
		this.idUnidadDocumental = idUnidadDocumental;
		this.idTipoUD = idTipoUD;
		this.idArea = idArea;
		this.idContenedor = idContenedor;
	}
	
	
	
	
	
}

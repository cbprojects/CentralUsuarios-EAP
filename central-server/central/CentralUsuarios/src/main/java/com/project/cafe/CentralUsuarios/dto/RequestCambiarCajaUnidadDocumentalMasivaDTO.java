package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;


public class RequestCambiarCajaUnidadDocumentalMasivaDTO implements Serializable {	
	
	
	private static final long serialVersionUID = 7803823950929548062L;
	private Long idCajaUno;
	private Long idCajaDos;
	private List<UnidadDocumentalTB> lstUnidadDocumentalCajaUno;
	private List<UnidadDocumentalTB> lstUnidadDocumentalCajaDos;

	public Long getIdCajaUno() {
		return idCajaUno;
	}



	public void setIdCajaUno(Long idCajaUno) {
		this.idCajaUno = idCajaUno;
	}



	public Long getIdCajaDos() {
		return idCajaDos;
	}



	public void setIdCajaDos(Long idCajaDos) {
		this.idCajaDos = idCajaDos;
	}



	public RequestCambiarCajaUnidadDocumentalMasivaDTO() {

	}



	public List<UnidadDocumentalTB> getLstUnidadDocumentalCajaUno() {
		return lstUnidadDocumentalCajaUno;
	}



	public void setLstUnidadDocumentalCajaUno(List<UnidadDocumentalTB> lstUnidadDocumentalCajaUno) {
		this.lstUnidadDocumentalCajaUno = lstUnidadDocumentalCajaUno;
	}



	public List<UnidadDocumentalTB> getLstUnidadDocumentalCajaDos() {
		return lstUnidadDocumentalCajaDos;
	}



	public void setLstUnidadDocumentalCajaDos(List<UnidadDocumentalTB> lstUnidadDocumentalCajaDos) {
		this.lstUnidadDocumentalCajaDos = lstUnidadDocumentalCajaDos;
	}

}

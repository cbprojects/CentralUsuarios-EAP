package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;


public class ResponseConsultarUnidadDocumentalMasivaDTO implements Serializable {	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4207061070431987739L;
	
	private List<UnidadDocumentalTB> lstUnidadDocumentalCajaUno;
	private List<UnidadDocumentalTB> lstUnidadDocumentalCajaDos;
	

	
	public ResponseConsultarUnidadDocumentalMasivaDTO() {

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

package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

//@XmlRootElement
public class CajaListDTO implements Serializable {
	
	private static final long serialVersionUID = 1377669280563051486L;
	
	public Long idCaja;
	public String codigoCaja;
	public List<UdListDTO> lstUdTotales;
	public Long getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(Long idCaja) {
		this.idCaja = idCaja;
	}
	public String getCodigoCaja() {
		return codigoCaja;
	}
	public void setCodigoCaja(String codigoCaja) {
		this.codigoCaja = codigoCaja;
	}
	public List<UdListDTO> getLstUdTotales() {
		return lstUdTotales;
	}
	public void setLstUdTotales(List<UdListDTO> lstUdTotales) {
		this.lstUdTotales = lstUdTotales;
	}
	public CajaListDTO(Long idCaja, String codigoCaja, List<UdListDTO> lstUdTotales) {
		super();
		this.idCaja = idCaja;
		this.codigoCaja = codigoCaja;
		this.lstUdTotales = lstUdTotales;
	}
	public CajaListDTO() {
		
	}
	
	
	

	
	

}

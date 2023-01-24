package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;

//@XmlRootElement
public class RequestCrearMasivoDTO implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 634041829959815196L;
	
	public Long tipoMasivo;
	public MasivoDTO masivoDTO;
	
	public Long getTipoMasivo() {
		return tipoMasivo;
	}
	public void setTipoMasivo(Long tipoMasivo) {
		this.tipoMasivo = tipoMasivo;
	}
	public MasivoDTO getMasivoDTO() {
		return masivoDTO;
	}
	public void setMasivoDTO(MasivoDTO masivoDTO) {
		this.masivoDTO = masivoDTO;
	}
	
	
	
	
}

package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.project.cafe.CentralUsuarios.model.PrestamoHistoricoTB;
import com.project.cafe.CentralUsuarios.model.PrestamoTB;

public class ResponseConsultarPrestamoDTO implements Serializable {

	private static final long serialVersionUID = -5423193572387070575L;
	
	private Boolean tienePrestamo;

	private PrestamoTB prestamo;

	private List<PrestamoHistoricoTB> listaPrestamo;

	public Boolean getTienePrestamo() {
		return tienePrestamo;
	}

	public void setTienePrestamo(Boolean tienePrestamo) {
		this.tienePrestamo = tienePrestamo;
	}

	public PrestamoTB getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(PrestamoTB prestamo) {
		this.prestamo = prestamo;
	}

	public List<PrestamoHistoricoTB> getListaPrestamo() {
		return listaPrestamo;
	}

	public void setListaPrestamo(List<PrestamoHistoricoTB> listaPrestamo) {
		this.listaPrestamo = listaPrestamo;
	}
	
	public ResponseConsultarPrestamoDTO(Boolean tienePrestamo, PrestamoTB prestamo,
			List<PrestamoHistoricoTB> listaPrestamo) {
		super();
		this.tienePrestamo = tienePrestamo;
		this.prestamo = prestamo;
		this.listaPrestamo = listaPrestamo;
	}

	public ResponseConsultarPrestamoDTO() {

	}

}

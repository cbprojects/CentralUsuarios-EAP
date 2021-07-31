package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class DashBoardFacturaDTO implements Serializable {

	private static final long serialVersionUID = -2409036899094080625L;
	private String cliente;
	private String empresa;
	private String estado;
	private String fechaCreacion;
	private String numero;
	private String precio;
	private String vatNo;

	public DashBoardFacturaDTO() {

	}

	public DashBoardFacturaDTO(String cliente, String empresa, String estado, String fechaCreacion, String numero,
			String precio, String vatNo) {
		this.cliente = cliente;
		this.empresa = empresa;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.numero = numero;
		this.precio = precio;
		this.vatNo = vatNo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

}

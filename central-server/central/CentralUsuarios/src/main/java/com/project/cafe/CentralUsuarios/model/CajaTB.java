package com.project.cafe.CentralUsuarios.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gdo_caja_tb")
public class CajaTB extends BaseEntidadTB implements Serializable {

	private static final long serialVersionUID = -5717553106675875507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "caj_id", nullable = false)
	private long id;

	@NotNull
	@Column(name = "caj_descripcion", nullable = true, length = 50)
	private String descripcion;

	@NotNull
	@Column(name = "caj_codigo_alterno", nullable = false, length = 10)
	private String codigoAlterno;

	@NotNull
	@ManyToOne( optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private EntrepanoTB entrepano;

	@NotNull
	@Column(name = "caj_codigo_barras", nullable = false, length = 128)
	private String codigoBarras;

	@NotNull
	@Column(name = "caj_QR", nullable = true, length = 128)
	private String qr;

	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private SociedadTB sociedad;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoAlterno() {
		return codigoAlterno;
	}

	public void setCodigoAlterno(String codigoAlterno) {
		this.codigoAlterno = codigoAlterno;
	}

	public EntrepanoTB getEntrepano() {
		return entrepano;
	}

	public void setEntrepano(EntrepanoTB entrepano) {
		this.entrepano = entrepano;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public SociedadTB getSociedad() {
		return sociedad;
	}
	
	public void setSociedad(SociedadTB sociedad) {
		this.sociedad = sociedad;
	}

}

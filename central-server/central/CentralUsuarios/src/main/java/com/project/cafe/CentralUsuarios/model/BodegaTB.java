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
@Table(name = "gdo_bodega_tb")
public class BodegaTB extends BaseEntidadTB implements Serializable {

	private static final long serialVersionUID = 4642045038715700170L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "bod_id", nullable = false)
	private long id;

	@NotNull
	@Column(name = "bod_nombre", nullable = false, length = 50)
	private String nombre;

	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private SedeTB sede;

	@NotNull
	@Column(name = "bod_nombre10", nullable = false, length = 10)
	private String nombre10;

	@NotNull
	@Column(name = "bod_OwnerName", nullable = false, length = 100)
	private String ownerName;

	@NotNull
	@Column(name = "bod_codigo", nullable = false, length = 10)
	private String codigo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public SedeTB getSede() {
		return sede;
	}

	public void setSede(SedeTB sede) {
		this.sede = sede;
	}

	public String getNombre10() {
		return nombre10;
	}

	public void setNombre10(String nombre10) {
		this.nombre10 = nombre10;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

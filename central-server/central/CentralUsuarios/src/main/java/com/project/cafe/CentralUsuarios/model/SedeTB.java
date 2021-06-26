package com.project.cafe.CentralUsuarios.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "gdo_sede_tb")
public class SedeTB extends BaseEntidadTB implements Serializable {
	
	private static final long serialVersionUID = 4642045038715700170L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "sed_id", nullable = false)
	private long id;

	@NotNull
	@Column(name = "sed_nombre", nullable = false, length = 50)
	private String nombre;

	@NotNull
	@Column(name = "sed_address", nullable = false, length = 250)
	private String address;
	
	@NotNull
	@Column(name = "sed_nombre10", nullable = false, length = 10)
	private String nombre10;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNombre10() {
		return nombre10;
	}

	public void setNombre10(String nombre10) {
		this.nombre10 = nombre10;
	}

}

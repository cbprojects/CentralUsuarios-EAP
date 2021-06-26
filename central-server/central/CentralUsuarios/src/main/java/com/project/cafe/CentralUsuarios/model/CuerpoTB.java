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
@Table(name = "gdo_cuerpo_tb")
public class CuerpoTB extends BaseEntidadTB implements Serializable {

	private static final long serialVersionUID = -7647492613066824131L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "cue_id", nullable = false)
	private long id;

	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private BloqueTB bloque;

	@NotNull
	@Column(name = "cue_codigo", nullable = false, length = 10)
	private String codigo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BloqueTB getBloque() {
		return bloque;
	}

	public void setBloque(BloqueTB bloque) {
		this.bloque = bloque;	
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

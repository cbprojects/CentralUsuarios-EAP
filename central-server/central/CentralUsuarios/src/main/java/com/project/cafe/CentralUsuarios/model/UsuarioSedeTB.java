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
@Table(name = "gdo_usuario_sede_tb")
public class UsuarioSedeTB extends BaseEntidadTB implements Serializable {
	
	private static final long serialVersionUID = 3662372459913843461L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "use_id", nullable = false)
	private long id;

	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private UsuarioTB usuario;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private SedeTB sede;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UsuarioTB getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTB usuario) {
		this.usuario = usuario;
	}

	public SedeTB getSede() {
		return sede;
	}

	public void setSede(SedeTB sede) {
		this.sede = sede;
	}
	
}

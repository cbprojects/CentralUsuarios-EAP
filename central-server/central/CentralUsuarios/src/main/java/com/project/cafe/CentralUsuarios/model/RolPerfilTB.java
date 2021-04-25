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
@Table(name = "gdo_RolPerfil_tb")
public class RolPerfilTB extends BaseEntidadTB implements Serializable {

	private static final long serialVersionUID = -5811321693798058412L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "usu_id", nullable = false, length = 19)
	private long id;

	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private PerfilTB perfil;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private RolTB rol;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PerfilTB getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilTB perfil) {
		this.perfil = perfil;
	}

	public RolTB getRol() {
		return rol;
	}

	public void setRol(RolTB rol) {
		this.rol = rol;
	}

	public RolPerfilTB(long id, PerfilTB perfil, RolTB rol) {
		this.id = id;
		this.perfil = perfil;
		this.rol = rol;
	}

	public RolPerfilTB() {
		
	}

	
}

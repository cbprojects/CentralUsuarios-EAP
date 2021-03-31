package com.project.cafe.CentralUsuarios.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntidadTB {

	@NotNull
	@Column(name = "gdo_estado", nullable = false)
	private short estado;

	@NotNull
	@Column(name = "gdo_fecha_creacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	@NotNull
	@Column(name = "gdo_usuario_creacion", nullable = false)
	private String usuarioCreacion;

	@Column(name = "gdo_fecha_actualizacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;

	@NotNull
	@Column(name = "gdo_usuario_actualizacion", nullable = false)
	private String usuarioActualizacion;

	public short getEstado() {
		return estado;
	}

	public void setEstado(short estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

}

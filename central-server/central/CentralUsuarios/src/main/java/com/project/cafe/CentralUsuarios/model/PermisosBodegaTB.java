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
@Table(name = "gdo_permisos_bodega_tb")
public class PermisosBodegaTB extends BaseEntidadTB implements Serializable {

	private static final long serialVersionUID = -8116155291556096930L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "peb_id", nullable = false)
	private long id;

	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private BodegaTB bodega;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private UsuarioTB usuario;

	@Column(name = "peb_crear", nullable = true)
	private Boolean crear;
	
	@Column(name = "peb_editar", nullable = true)
	private Boolean editar;
	
	@Column(name = "peb_consultar", nullable = true)
	private Boolean consultar;
	
	@Column(name = "peb_eliminar", nullable = true)
	private Boolean eliminar;

	
}

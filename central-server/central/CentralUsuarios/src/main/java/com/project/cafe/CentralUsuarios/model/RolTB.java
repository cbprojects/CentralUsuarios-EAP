package com.project.cafe.CentralUsuarios.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gdo_rol_tb")
public class RolTB extends BaseEntidadTB implements Serializable {
	private static final long serialVersionUID = 2974228400627528478L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "rol_id", nullable = false, length = 19)
	private long id;

	@NotNull
	@Column(name = "rol_codigo", nullable = false, length = 30)
	private String codigo;

	@NotNull
	@Column(name = "rol_descripcion", nullable = false, length = 250)
	private String apellidos;

}

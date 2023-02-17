package com.project.cafe.CentralUsuarios.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "gdo_prestamo_historico_tb")
public class PrestamoHistoricoTB extends BaseEntidadTB implements Serializable {

	
	private static final long serialVersionUID = -517597257466954808L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "prehis_id", nullable = false)
	private long id;
	
	@NotNull
	@Column(name = "prehis_idUd", nullable = false)
	private long idUd;

	@Column(name = "prehis_fecha", nullable = false)
	private Date fechaPrestamo;
	
	@NotNull
	@Column(name = "prehis_responsable", nullable = false, length = 100)
	private String responsable;

	@Column(name = "prehis_observacion", nullable = true, length = 500)
	private String observacion;

	
}

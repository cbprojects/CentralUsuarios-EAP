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
@Table(name = "gdo_acta_tb")
public class ActaTB extends BaseEntidadTB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885720730594579701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "act_id", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "act_numeroFactura", nullable = true)
	private Long numeroFactura;
	
	@NotNull
	@Column(name = "act_cantidad", nullable = true)
	private Long cantidad;
	
	@NotNull
	@Column(name = "act_aprobada", nullable = true)
	private Boolean aprobada;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private UsuarioTB usuario;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private ClienteTB cliente;
	
}

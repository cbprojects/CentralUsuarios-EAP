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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gdo_bloque_tb")
public class BloqueTB extends BaseEntidadTB implements Serializable {

	private static final long serialVersionUID = -5870107696118219413L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "blo_id", nullable = false)
	private long id;

	@NonNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private BodegaTB bodega;

	@NonNull
	@Column(name = "blo_codigo", nullable = false, length = 10)
	private String codigo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BodegaTB getBodega() {
		return bodega;
	}

	public void setBodega(BodegaTB bodega) {
		this.bodega = bodega;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

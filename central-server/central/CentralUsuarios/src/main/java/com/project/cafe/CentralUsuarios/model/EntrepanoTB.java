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
@Table(name = "gdo_entrepano_tb")
public class EntrepanoTB extends BaseEntidadTB implements Serializable {

	private static final long serialVersionUID = -5723022725913715868L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "ent_id", nullable = false)
	private long id;

	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private EstanteTB estante;

	@NotNull
	@Column(name = "ent_codigo", nullable = false, length = 10)
	private String codigo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EstanteTB getEstante() {
		return estante;
	}

	public void setEstante(EstanteTB estante) {
		this.estante = estante;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}

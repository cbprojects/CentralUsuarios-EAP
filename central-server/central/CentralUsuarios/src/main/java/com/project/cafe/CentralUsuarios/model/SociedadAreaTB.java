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
@Table(name = "gdo_sociedad_area_tb")
public class SociedadAreaTB extends BaseEntidadTB implements Serializable {

	private static final long serialVersionUID = -4009257478597911304L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "soa_id", nullable = false)
	private long id;

	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private SociedadTB sociedad;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private AreaTB area;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SociedadTB getSociedad() {
		return sociedad;
	}

	public void setSociedad(SociedadTB sociedad) {
		this.sociedad = sociedad;
	}

	public AreaTB getArea() {
		return area;
	}

	public void setArea(AreaTB area) {
		this.area = area;
	}
	
	

}

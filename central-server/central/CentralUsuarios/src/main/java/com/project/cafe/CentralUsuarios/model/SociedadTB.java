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
@Table(name = "gdo_sociedad_tb")
public class SociedadTB extends BaseEntidadTB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8535563409385003354L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "soc_id", nullable = false)
	private long id;

	@NotNull
	@Column(name = "soc_nombre", nullable = false, length = 50)
	private String nombre;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private ClienteTB cliente;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private ServidorTB servidor;

	@NotNull
	@Column(name = "soc_Tax_Id", nullable = true, length = 20)
	private String tax;
	
	@NotNull
	@Column(name = "soc_nombre_10", nullable = true, length = 10)
	private String nombre10;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ClienteTB getCliente() {
		return cliente;
	}

	public void setCliente(ClienteTB cliente) {
		this.cliente = cliente;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getNombre10() {
		return nombre10;
	}

	public void setNombre10(String nombre10) {
		this.nombre10 = nombre10;
	}

	public ServidorTB getServidor() {
		return servidor;
	}

	public void setServidor(ServidorTB servidor) {
		this.servidor = servidor;
	}

}

package com.project.cafe.CentralUsuarios.model;

import java.io.Serializable;

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
@Table(name = "gdo_servidor_tb")
public class ServidorTB extends BaseEntidadTB implements Serializable {
	
	
	private static final long serialVersionUID = -2174930906841328157L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "ser_id", nullable = false)
	private long id;

	@NotNull
	@Column(name = "ser_ip", nullable = false, length = 50)
	private String ip;

	@NotNull
	@Column(name = "ser_puerto", nullable = false, length = 50)
	private String puerto;
	
	@NotNull
	@Column(name = "ser_usuario", nullable = false, length = 50)
	private String usuario;

	@NotNull
	@Column(name = "ser_clave", nullable = false, length = 50)
	private String clave;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}

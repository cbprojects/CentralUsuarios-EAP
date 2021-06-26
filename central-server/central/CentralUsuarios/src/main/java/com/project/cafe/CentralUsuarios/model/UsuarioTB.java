package com.project.cafe.CentralUsuarios.model;

import java.io.Serializable;

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
@Table(name = "gdo_usuario_tb")
public class UsuarioTB extends BaseEntidadTB implements Serializable {

	private static final long serialVersionUID = -589354145363174761L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "usu_id", nullable = false, length = 19)
	private long id;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private PerfilTB perfil;
	
	@NotNull
	@Column(name = "usu_contrasena", nullable = false, length = 100)
	private String contrasena;

	@NotNull
	@Column(name = "usu_tipo_documento", nullable = false, length = 3)
	private int tipoDocumento;
	
	@NotNull
	@Column(name = "usu_documento", nullable = false, length = 15)
	private String documento;
	
	@NotNull
	@Column(name = "usu_nombre", nullable = false, length = 100)
	private String nombre;
	
	@NotNull
    @Column(name = "usu_celular", nullable = false, length = 100)
    private String celular;

	@NotNull
    @Column(name = "usu_direccion", nullable = false, length = 100)
    private String direccion;

	@NotNull
    @Column(name = "usu_email", nullable = false, length = 100)
    private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PerfilTB getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilTB perfil) {
		this.perfil = perfil;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}

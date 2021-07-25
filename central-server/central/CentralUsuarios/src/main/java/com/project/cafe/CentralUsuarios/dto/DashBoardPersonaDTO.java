package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;


public class DashBoardPersonaDTO implements Serializable {

	private static final long serialVersionUID = -1446966367179839146L;
	private String activity;
	private String commit;
	private String fechaCommit;
	private String fechaRegistro;
	private String nombre;
	private String payment;
	private String rutaImagen;
	private String satisfaction;
	private String usage;
	private String usuario;
	
	
	public DashBoardPersonaDTO(String activity, String commit, String fechaCommit, String fechaRegistro, String nombre,
			String payment, String rutaImagen, String satisfaction, String usage, String usuario) {
		this.activity = activity;
		this.commit = commit;
		this.fechaCommit = fechaCommit;
		this.fechaRegistro = fechaRegistro;
		this.nombre = nombre;
		this.payment = payment;
		this.rutaImagen = rutaImagen;
		this.satisfaction = satisfaction;
		this.usage = usage;
		this.usuario = usuario;
	}

	public DashBoardPersonaDTO() {

	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getCommit() {
		return commit;
	}

	public void setCommit(String commit) {
		this.commit = commit;
	}

	public String getFechaCommit() {
		return fechaCommit;
	}

	public void setFechaCommit(String fechaCommit) {
		this.fechaCommit = fechaCommit;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}

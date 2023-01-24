package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.RolTB;


public class RequestCrearRolPerfilDTO  implements Serializable  {

	private static final long serialVersionUID = -3088924422164447360L;

	private PerfilTB perfil;

	private List<RolTB> lstRoles;
	
	private String user;

	
	public List<RolTB> getLstRoles() {
		return lstRoles;
	}

	public void setLstRoles(List<RolTB> lstRoles) {
		this.lstRoles = lstRoles;
	}

	public PerfilTB getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilTB perfil) {
		this.perfil = perfil;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public RequestCrearRolPerfilDTO(PerfilTB perfil, List<RolTB> lstRoles, String user) {
		super();
		this.perfil = perfil;
		this.lstRoles = lstRoles;
		this.user = user;
	}

	public RequestCrearRolPerfilDTO() {
		super();
	}

}

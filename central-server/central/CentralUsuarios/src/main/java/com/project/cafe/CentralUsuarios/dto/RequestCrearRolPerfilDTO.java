package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.RolTB;


public class RequestCrearRolPerfilDTO  implements Serializable  {

	private static final long serialVersionUID = -3088924422164447360L;

	private PerfilTB perfil;

	private List<RolTB> lstRoles;

	
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

	

	public RequestCrearRolPerfilDTO(PerfilTB perfil, List<RolTB> lstRoles) {
		this.perfil = perfil;
		this.lstRoles = lstRoles;
	}

	public RequestCrearRolPerfilDTO() {
		
	}

	

}

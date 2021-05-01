package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.project.cafe.CentralUsuarios.model.RolTB;



public class ResponseConsultarRolPerfilDTO implements Serializable  {

	private static final long serialVersionUID = 6792049558917649354L;

	private List<RolTB> rolesAsociados;

	private List<RolTB> rolesNoAsociados;

	

	public List<RolTB> getRolesAsociados() {
		return rolesAsociados;
	}



	public void setRolesAsociados(List<RolTB> rolesAsociados) {
		this.rolesAsociados = rolesAsociados;
	}



	public List<RolTB> getRolesNoAsociados() {
		return rolesNoAsociados;
	}



	public void setRolesNoAsociados(List<RolTB> rolesNoAsociados) {
		this.rolesNoAsociados = rolesNoAsociados;
	}



	public ResponseConsultarRolPerfilDTO(List<RolTB> rolesAsociados, List<RolTB> rolesNoAsociados) {
		this.rolesAsociados = rolesAsociados;
		this.rolesNoAsociados = rolesNoAsociados;
	}



	public ResponseConsultarRolPerfilDTO() {
		
	}

}

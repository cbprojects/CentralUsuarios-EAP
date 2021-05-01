package com.project.cafe.CentralUsuarios.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.RequestCrearRolPerfilDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarRolPerfilDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseCrearRolPerfilDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.model.RolPerfilTB;
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.service.IRolPerfilService;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;

@RestController
@RequestMapping("/central/rolPerfil")
public class ControladorRestRolPerfil {

	@Value("${email.servidor}")
	private String EMAIL_SERVIDOR;

	@Autowired
	private IRolPerfilService rolPerfilService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearRolPerfil")
	public ResponseEntity<ResponseCrearRolPerfilDTO> crearPerfil(@RequestBody RequestCrearRolPerfilDTO requestCrearRolPErfil) {
		try {
			// eliminamos los datos en RolPerfilTB para el perfil enviado
			rolPerfilService.eliminarRolPerfilMasivoXPerfil(requestCrearRolPErfil.getPerfil().getId());
			// Creamos los RolPerfilTb Segun La LIsta de Roles
			if (requestCrearRolPErfil.getLstRoles() != null && !requestCrearRolPErfil.getLstRoles().isEmpty()) {
				RolPerfilTB crearRolPerfil;
				Short activo=1;
				for (RolTB rolTB : requestCrearRolPErfil.getLstRoles()) {
					crearRolPerfil = new RolPerfilTB();
					crearRolPerfil.setPerfil(requestCrearRolPErfil.getPerfil());
					crearRolPerfil.setRol(rolTB);
					crearRolPerfil.setEstado(activo);
					rolPerfilService.crearRolPerfil(crearRolPerfil);
				}
			}
			ResponseCrearRolPerfilDTO response = new ResponseCrearRolPerfilDTO();
			response.setCodigo(PropertiesUtil.getProperty("centralusuarios.msg.validate.string.cero"));
			response.setMensaje(PropertiesUtil.getProperty("centralusuarios.msg.validate.string.mensaje.exito"));
			return new ResponseEntity<ResponseCrearRolPerfilDTO>(response, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarRolPerfilFiltros")
	public ResponseEntity<ResponseConsultarRolPerfilDTO> consultarRolPerfil(@RequestBody PerfilTB perfil) {
		try {
			if(perfil != null && perfil.getId() != 0) {
				ResponseConsultarRolPerfilDTO response=new ResponseConsultarRolPerfilDTO();
				// traer los roles por perfil asociados al perfil seleccionado
				List<RolTB> rolesAsociados= new ArrayList<RolTB>();
				rolesAsociados=rolPerfilService.BuscarRolesSegunPerfil(perfil.getId());
				response.setRolesAsociados(rolesAsociados);
				
				//Traer roles no asociados 
				List<RolTB> rolesNoAsociados= new ArrayList<RolTB>();
				rolesNoAsociados=rolPerfilService.BuscarRolesNoAsociadosSegunPerfil(perfil.getId());
				response.setRolesNoAsociados(rolesNoAsociados);
				
				
				return new ResponseEntity<ResponseConsultarRolPerfilDTO>(response, HttpStatus.OK); 
			}else {
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
				String mensajeErrores = ConstantesValidaciones.MSG_BUSQUEDA_ROLPERFIL_VACIO;

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}
			
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}


}

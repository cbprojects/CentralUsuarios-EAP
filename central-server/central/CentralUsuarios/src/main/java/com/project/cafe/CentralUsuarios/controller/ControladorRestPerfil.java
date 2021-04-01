package com.project.cafe.CentralUsuarios.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.MailDTO;
import com.project.cafe.CentralUsuarios.enums.EEstado;
import com.project.cafe.CentralUsuarios.enums.EGenero;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.PerfilTB;
import com.project.cafe.CentralUsuarios.service.IPerfilService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;
import com.project.cafe.CentralUsuarios.util.UtilMail;

@RestController
@RequestMapping("/central/usuarios")
public class ControladorRestPerfil {

	@Value("${email.servidor}")
	private String EMAIL_SERVIDOR;

	@Autowired
	private UtilMail mailUtil;

	@Autowired
	private IPerfilService perfilService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearPerfil")
	public ResponseEntity<PerfilTB> crearPerfil(@RequestBody PerfilTB perfilAutor) {
		try {
			PerfilTB newPerfil = new PerfilTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_AUTOR_TB, perfilAutor);
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarRolUnicoCrear(perfilAutor.getCodigo())) {
					newPerfil = perfilService.crearPerfil(perfilAutor);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_PERFIL_REPETIDO;

					throw new ModelNotFoundException(erroresTitle + mensajeErrores);
				}
			} else {
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
				StringBuilder mensajeErrores = new StringBuilder();
				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}
			return new ResponseEntity<PerfilTB>(newPerfil, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarRolUnicoCrear(String codigo) {
		List<PerfilTB> listaPerfiles = perfilService.buscarPerfilPorCodigo(codigo);
		if (listaPerfiles == null || listaPerfiles.isEmpty()) {
			return true;
		}
		return false;
	}

	// UPDATE
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarPerfil")
	public ResponseEntity<PerfilTB> modificarPerfil(@RequestBody PerfilTB perfilAutor) {
		try {
			PerfilTB newPerfil = new PerfilTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_AUTOR_TB, perfilAutor);
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarRolUnicoEditar(perfilAutor.getCodigo(),perfilAutor.getId())) {
					newPerfil = perfilService.modificarPerfil(perfilAutor);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_PERFIL_REPETIDO;

					throw new ModelNotFoundException(erroresTitle + mensajeErrores);
				}
			} else {
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
				StringBuilder mensajeErrores = new StringBuilder();
				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}
			return new ResponseEntity<PerfilTB>(newPerfil, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
		
	}

	private boolean validarRolUnicoEditar(String codigo, long id) {
		List<PerfilTB> listaPerfiles = perfilService.buscarPerfilPorCodigo(codigo);
		if (listaPerfiles == null || listaPerfiles.isEmpty()) {
			return true;
		}else {
			if(listaPerfiles.get(0).getId()==(id)){
				return true;
			}
		}
		return false;
	}
	
//	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@RequestMapping("/consultarPerfil")
//	public ResponseEntity<PerfilTB> crearPerfil(@RequestBody PerfilTB perfilAutor) {
//		
//	}
	
}

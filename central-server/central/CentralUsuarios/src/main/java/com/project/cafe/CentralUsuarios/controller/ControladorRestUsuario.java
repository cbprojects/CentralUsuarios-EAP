package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuariosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;
import com.project.cafe.CentralUsuarios.service.IUsuarioService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PasswordUtil;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/usuario")
public class ControladorRestUsuario {

	@Autowired
	private IUsuarioService usuarioService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearUsuario")
	public ResponseEntity<UsuarioTB> crearUsuario(@RequestBody UsuarioTB usuario) {
		try {
			usuario.setEmail(PasswordUtil.encriptarAES(usuario.getEmail(), ConstantesValidaciones.CLAVE_AES));
			usuario.setCelular(PasswordUtil.encriptarAES(usuario.getCelular(), ConstantesValidaciones.CLAVE_AES));
			usuario.setDireccion(PasswordUtil.encriptarAES(usuario.getDireccion(), ConstantesValidaciones.CLAVE_AES));
			usuario.setContrasena(PasswordUtil.encriptarAES(usuario.getContrasena(), ConstantesValidaciones.CLAVE_AES));
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_TB, usuario);

			UsuarioTB newUsuario = new UsuarioTB();
			if (errores.isEmpty()) {
				// validar usuario unico
				if (validarUsuarioUnicoCrear(usuario.getEmail())) {
					newUsuario = usuarioService.crearUsuario(usuario);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_USUARIO_REPETIDO;

					throw new ModelNotFoundException(erroresTitle + mensajeErrores);
				}

			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<UsuarioTB>(newUsuario, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarUsuarioUnicoCrear(String email) {
		List<UsuarioTB> listaUsuarios = usuarioService.buscarUsuarioPorEmail(email);
		if (listaUsuarios == null || listaUsuarios.isEmpty()) {
			return true;
		}
		return false;
	}

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarUsuario")
	public ResponseEntity<UsuarioTB> modificarUsuario(@RequestBody UsuarioTB usuario) {
		try {
			usuario.setEmail(PasswordUtil.encriptarAES(usuario.getEmail(), ConstantesValidaciones.CLAVE_AES));
			usuario.setCelular(PasswordUtil.encriptarAES(usuario.getCelular(), ConstantesValidaciones.CLAVE_AES));
			usuario.setDireccion(PasswordUtil.encriptarAES(usuario.getDireccion(), ConstantesValidaciones.CLAVE_AES));
			usuario.setContrasena(PasswordUtil.encriptarAES(usuario.getContrasena(), ConstantesValidaciones.CLAVE_AES));
			UsuarioTB newUsuario = new UsuarioTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_TB, usuario);
			if (errores.isEmpty()) {
				// validar usuario unico
				if (validarUsuarioUnicoEditar(usuario.getEmail(), usuario.getId())) {
					newUsuario = usuarioService.modificarUsuario(usuario);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_USUARIO_REPETIDO;

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
			return new ResponseEntity<UsuarioTB>(newUsuario, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}

	private boolean validarUsuarioUnicoEditar(String email, long id) {
		List<UsuarioTB> listaUsuario = usuarioService.buscarUsuarioPorEmail(email);
		if (listaUsuario == null || listaUsuario.isEmpty()) {
			return true;
		} else {
			if (listaUsuario.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUsuarioFiltros")
	public ResponseConsultarDTO<UsuarioTB> consultarUsuarioFiltros(@RequestBody RequestConsultarUsuariosDTO request) {
		try {
			
			return usuarioService.consultarUsusarioFiltros(request);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

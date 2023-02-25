package com.project.cafe.CentralUsuarios.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.net.URL;
import java.util.HashMap;

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
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuariosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;

import com.project.cafe.CentralUsuarios.dto.ResponseLoginUsuarioDTO;


import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.model.UsuarioTB;
import com.project.cafe.CentralUsuarios.service.IRolPerfilService;
import com.project.cafe.CentralUsuarios.service.IUsuarioService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PasswordUtil;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;
import com.project.cafe.CentralUsuarios.util.UtilMail;

@RestController
@RequestMapping("/central/usuario")
public class ControladorRestUsuario {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IRolPerfilService rolPerfilService;

	@Value("${email.servidor}")
	private String EMAIL_SERVIDOR;

	@Value("${ruta.recordar.clave}")
	private String RUTA_RECORDAR_CLAVE;

	@Autowired
	private UtilMail mailUtil;


	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearUsuario")
	public ResponseEntity<UsuarioTB> crearUsuario(@RequestBody UsuarioTB usuario) {
		try {
			usuario.setCelular(PasswordUtil.encriptarAES(usuario.getCelular(), ConstantesValidaciones.CLAVE_AES));
			usuario.setDireccion(PasswordUtil.encriptarAES(usuario.getDireccion(), ConstantesValidaciones.CLAVE_AES));
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_TB, usuario);

			UsuarioTB newUsuario = new UsuarioTB();
			if (errores.isEmpty()) {
				// validar usuario unico
				if (validarUsuarioUnicoCrear(usuario.getEmail())) {
					newUsuario = usuarioService.crearUsuario(usuario);
					enviarCorreo(newUsuario);
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
			usuario.setCelular(PasswordUtil.encriptarAES(usuario.getCelular(), ConstantesValidaciones.CLAVE_AES));
			usuario.setDireccion(PasswordUtil.encriptarAES(usuario.getDireccion(), ConstantesValidaciones.CLAVE_AES));
			if(StringUtils.isNotBlank(usuario.getContrasena())) {
				usuario.setContrasena(PasswordUtil.encriptarAES(usuario.getContrasena(), ConstantesValidaciones.CLAVE_AES));
			}
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


	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/restaurarClave")
	public ResponseEntity<UsuarioTB> restaurarClave(@RequestBody UsuarioTB usuario){
		try {
			UsuarioTB usuarioActivado = null;
			if (usuario != null && !StringUtils.isBlank(usuario.getEmail())) {
				List<UsuarioTB> usuariosEncontrados = usuarioService.buscarUsuarioPorEmail(usuario.getEmail());
				if (usuariosEncontrados != null && !usuariosEncontrados.isEmpty()) {
					usuarioActivado = usuariosEncontrados.get(0);
					if (usuarioActivado != null) {
						enviarCorreo(usuarioActivado);
					} else {
						throw new ModelNotFoundException(ConstantesValidaciones.ERROR_RESTAURAR_CLAVE.toString());
					}
				} else {
					throw new ModelNotFoundException(ConstantesValidaciones.ERROR_RESTAURAR_CLAVE.toString());
				}
			} else {
				throw new ModelNotFoundException(ConstantesValidaciones.ERROR_RESTAURAR_CLAVE);
			}

			return new ResponseEntity<UsuarioTB>(usuarioActivado, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/loginUsuario")
	public ResponseEntity<ResponseLoginUsuarioDTO> loginUsuario(@RequestBody UsuarioTB usuario) {
		try {
			Optional <UsuarioTB> usuarioLogueado = null;
			if (usuario != null && !StringUtils.isBlank(usuario.getEmail())
					&& !StringUtils.isBlank(usuario.getContrasena())) {
				String user= usuario.getEmail();
				String clave = PasswordUtil.encriptarAES(usuario.getContrasena(), ConstantesValidaciones.CLAVE_AES);
				usuarioLogueado = usuarioService.loginUsuario(user, clave);
				if (!usuarioLogueado.isPresent()) {
					throw new ModelNotFoundException(
							ConstantesValidaciones.ERROR_LOGIN_DATOS_INCORRECTOS_INACTIVOS.toString());
				}else {
					ResponseLoginUsuarioDTO response= new ResponseLoginUsuarioDTO();
					response.setUsuario(usuarioLogueado.get());
					List<RolTB> listaRoles= new ArrayList<>();
					listaRoles=rolPerfilService.BuscarRolesSegunPerfil(usuarioLogueado.get().getPerfil().getId());
					response.setListaRoles(listaRoles);	
					return new ResponseEntity<ResponseLoginUsuarioDTO>(response, HttpStatus.OK);
				}
			} else {
				throw new ModelNotFoundException(ConstantesValidaciones.ERROR_LOGIN_DATOS_INSUFICIENTES);
			}
		} catch (JSONException  e ) {
			throw new ModelNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	private void enviarCorreo(UsuarioTB usuario) {
		
		MailDTO mailDto = new MailDTO();
		mailDto.setFrom(EMAIL_SERVIDOR);
		mailDto.setTo(usuario.getEmail());
		mailDto.setSubject("RESTAURAR CLAVE");

		Map<String, Object> model = new HashMap<>();
		model.put("user", usuario.getEmail());
		model.put("nombreCompleto", usuario.getNombre());
		model.put("email", usuario.getEmail());
		String urlRuta = RUTA_RECORDAR_CLAVE + usuario.getEmail();
		try {
			model.put("resetUrl", new URL(urlRuta).toURI().toASCIIString());
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
		mailDto.setModel(model);

		mailUtil.sendMail(mailDto, ConstantesValidaciones.TEMPLATE_MAIL_RECORDAR_CLAVE);
	}
	
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUsuarioActivo")
	public List<UsuarioTB> consultarUsuarioActivo() {
		try {
			return usuarioService.consultarUsuarioActivo();
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

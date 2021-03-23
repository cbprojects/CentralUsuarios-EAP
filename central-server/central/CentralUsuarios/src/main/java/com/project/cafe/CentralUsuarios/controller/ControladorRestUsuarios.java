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
import com.project.cafe.CentralUsuarios.model.UsuarioAutorTB;
import com.project.cafe.CentralUsuarios.service.IUsuariosService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;
import com.project.cafe.CentralUsuarios.util.UtilMail;

@RestController
@RequestMapping("/eutanasia/paratodos")
public class ControladorRestUsuarios {

	@Value("${email.servidor}")
	private String EMAIL_SERVIDOR;

	@Value("${ruta.verificar.cuenta.nueva}")
	private String URL_VERIFICAR_CUENTA_NUEVA;

	@Value("${ruta.recordar.clave}")
	private String URL_RECORDAR_CLAVE;

	@Autowired
	private UtilMail mailUtil;

	@Autowired
	private IUsuariosService usuariosService;

	// SELECT

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUsuarios")
	public ResponseEntity<List<UsuarioAutorTB>> consultarUsuarios() {
		try {
			List<UsuarioAutorTB> listaUsuarios = usuariosService.consultarUsuarios();
			return new ResponseEntity<List<UsuarioAutorTB>>(listaUsuarios, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUsuariosPorFiltros")
	public ResponseEntity<List<UsuarioAutorTB>> consultarUsuariosPorFiltros(@RequestBody UsuarioAutorTB usuarioAutor) {
		try {
			List<UsuarioAutorTB> listaUsuarios = usuariosService.consultarUsuariosPorFiltros(usuarioAutor);
			return new ResponseEntity<List<UsuarioAutorTB>>(listaUsuarios, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// LOGIN

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/loginUsuario")
	public ResponseEntity<UsuarioAutorTB> loginUsuario(@RequestBody UsuarioAutorTB usuarioAutor) {
		try {
			UsuarioAutorTB usuarioLogueado = null;
			if (usuarioAutor != null && !StringUtils.isBlank(usuarioAutor.getUsuario())
					&& !StringUtils.isBlank(usuarioAutor.getPassword())) {
				usuarioLogueado = usuariosService.loginUsuario(usuarioAutor.getUsuario(), usuarioAutor.getPassword());
				if (usuarioLogueado == null) {
					throw new ModelNotFoundException(
							ConstantesValidaciones.ERROR_LOGIN_DATOS_INCORRECTOS_INACTIVOS.toString());
				}
			} else {
				throw new ModelNotFoundException(ConstantesValidaciones.ERROR_LOGIN_DATOS_INSUFICIENTES);
			}

			return new ResponseEntity<UsuarioAutorTB>(usuarioLogueado, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/activarUsuario")
	public ResponseEntity<UsuarioAutorTB> activarUsuario(@RequestBody UsuarioAutorTB usuarioAutor) {
		try {
			UsuarioAutorTB usuarioActivado = null;
			if (usuarioAutor != null && !StringUtils.isBlank(usuarioAutor.getUsuario())
					&& !StringUtils.isBlank(usuarioAutor.getPassword())) {
				usuarioAutor.setEstado((short) EEstado.INACTIVO.ordinal());
				List<UsuarioAutorTB> usuariosEncontrados = usuariosService.consultarUsuarios();
				if (usuariosEncontrados != null && !usuariosEncontrados.isEmpty()) {
					for (UsuarioAutorTB user : usuariosEncontrados) {
						String passwordEncrypt = Util.encriptarPassword(user.getUsuario() + "|" + user.getPassword());
						if (passwordEncrypt.equals(usuarioAutor.getPassword())) {
							usuarioActivado = user;
							break;
						}
					}

					if (usuarioActivado != null) {
						usuarioActivado.setEstado((short) EEstado.ACTIVO.ordinal());
						usuarioActivado = usuariosService.modificarUsuario(usuarioActivado);
						if (usuarioActivado == null) {
							throw new ModelNotFoundException(
									ConstantesValidaciones.ERROR_LOGIN_DATOS_INCORRECTOS_INACTIVOS.toString());
						}
					} else {
						throw new ModelNotFoundException(
								ConstantesValidaciones.ERROR_LOGIN_DATOS_INCORRECTOS_INACTIVOS.toString());
					}
				} else {
					throw new ModelNotFoundException(
							ConstantesValidaciones.ERROR_LOGIN_DATOS_INCORRECTOS_INACTIVOS.toString());
				}
			} else {
				throw new ModelNotFoundException(ConstantesValidaciones.ERROR_LOGIN_DATOS_INSUFICIENTES);
			}

			return new ResponseEntity<UsuarioAutorTB>(usuarioActivado, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUsuarioEncriptado")
	public ResponseEntity<UsuarioAutorTB> consultarUsuarioEncriptado(@RequestBody UsuarioAutorTB usuarioAutor) {
		try {
			UsuarioAutorTB usuarioActivado = null;
			if (usuarioAutor != null && !StringUtils.isBlank(usuarioAutor.getUsuario())) {
				usuarioAutor.setEstado((short) EEstado.INACTIVO.ordinal());
				List<UsuarioAutorTB> usuariosEncontrados = usuariosService.consultarUsuarios();
				if (usuariosEncontrados != null && !usuariosEncontrados.isEmpty()) {
					for (UsuarioAutorTB user : usuariosEncontrados) {
						String usuarioEncrypt = Util.encriptarPassword(user.getUsuario());
						if (usuarioEncrypt.equals(usuarioAutor.getUsuario())) {
							usuarioActivado = user;
							break;
						}
					}

					if (usuarioActivado != null) {
						usuarioActivado.setPassword("");
					} else {
						throw new ModelNotFoundException(ConstantesValidaciones.ERROR_RESTAURAR_CLAVE.toString());
					}
				} else {
					throw new ModelNotFoundException(ConstantesValidaciones.ERROR_RESTAURAR_CLAVE.toString());
				}
			} else {
				throw new ModelNotFoundException(ConstantesValidaciones.ERROR_RESTAURAR_CLAVE);
			}

			return new ResponseEntity<UsuarioAutorTB>(usuarioActivado, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/restaurarClave")
	public ResponseEntity<UsuarioAutorTB> restaurarClave(@RequestBody UsuarioAutorTB usuarioAutor) {
		try {
			UsuarioAutorTB usuarioActivado = null;
			if (usuarioAutor != null && !StringUtils.isBlank(usuarioAutor.getUsuario())) {
				usuarioAutor.setEstado((short) EEstado.INACTIVO.ordinal());
				List<UsuarioAutorTB> usuariosEncontrados = usuariosService.consultarUsuarios();
				if (usuariosEncontrados != null && !usuariosEncontrados.isEmpty()) {
					for (UsuarioAutorTB user : usuariosEncontrados) {
						String usuarioEncrypt = Util.encriptarPassword(user.getUsuario());
						if (usuarioEncrypt.equals(Util.encriptarPassword(usuarioAutor.getUsuario()))) {
							usuarioActivado = user;
							break;
						}
					}

					if (usuarioActivado != null) {
						MailDTO mailDto = new MailDTO();
						mailDto.setFrom(EMAIL_SERVIDOR);
						mailDto.setTo(usuarioActivado.getCorreo());
						mailDto.setSubject("RESTAURAR CLAVE - EUTANASIA WEB PAGE");

						Map<String, Object> model = new HashMap<>();
						model.put("user", usuarioActivado.getUsuario());
						model.put("nombreCompleto",
								usuarioActivado.getNombres() + " " + usuarioActivado.getApellidos());
						model.put("email", usuarioActivado.getCorreo());
						String urlRuta = URL_RECORDAR_CLAVE + Util.encriptarPassword(usuarioActivado.getUsuario());
						try {
							model.put("resetUrl", new URL(urlRuta).toURI().toASCIIString());
						} catch (Exception e) {
							throw new ModelNotFoundException(e.getMessage());
						}
						mailDto.setModel(model);

						mailUtil.sendMail(mailDto, ConstantesValidaciones.TEMPLATE_MAIL_RECORDAR_CLAVE);
					} else {
						throw new ModelNotFoundException(ConstantesValidaciones.ERROR_RESTAURAR_CLAVE.toString());
					}
				} else {
					throw new ModelNotFoundException(ConstantesValidaciones.ERROR_RESTAURAR_CLAVE.toString());
				}
			} else {
				throw new ModelNotFoundException(ConstantesValidaciones.ERROR_RESTAURAR_CLAVE);
			}

			return new ResponseEntity<UsuarioAutorTB>(usuarioActivado, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearUsuario")
	public ResponseEntity<UsuarioAutorTB> crearUsuario(@RequestBody UsuarioAutorTB usuarioAutor) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_AUTOR_TB, usuarioAutor);
			// Filtrar por usuario
			UsuarioAutorTB filtroUsuario = new UsuarioAutorTB();
			filtroUsuario.setUsuario(usuarioAutor.getUsuario());
			List<UsuarioAutorTB> listaPorUsuario = usuariosService.consultarUsuariosPorFiltros(filtroUsuario);
			if (listaPorUsuario != null && !listaPorUsuario.isEmpty()) {
				errores.add(ConstantesValidaciones.MSG_USUARIO_REPETIDO);
			}

			// Filtrar por correo
			UsuarioAutorTB filtroCorreo = new UsuarioAutorTB();
			filtroCorreo.setCorreo(usuarioAutor.getCorreo());
			List<UsuarioAutorTB> listaPorCorreo = usuariosService.consultarUsuariosPorFiltros(filtroCorreo);
			if (listaPorCorreo != null && !listaPorCorreo.isEmpty()) {
				errores.add(ConstantesValidaciones.MSG_CORREO_REPETIDO);
			}

			UsuarioAutorTB newUsuario = new UsuarioAutorTB();
			if (errores.isEmpty()) {
				usuarioAutor.setEstado((short) EEstado.INACTIVO.ordinal());
				// usuarioAutor.setRol(ERolUsuario.FAN.ordinal());
				newUsuario = usuariosService.crearUsuario(usuarioAutor);
				if (newUsuario != null) {
					MailDTO mailDto = new MailDTO();
					mailDto.setFrom(EMAIL_SERVIDOR);
					mailDto.setTo(newUsuario.getCorreo());
					mailDto.setSubject("ACTIVACIÓN USUARIO NUEVO - EUTANASIA WEB PAGE");

					Map<String, Object> model = new HashMap<>();
					model.put("user", newUsuario.getUsuario());
					model.put("nombreCompleto", newUsuario.getNombres() + " " + newUsuario.getApellidos());
					model.put("email", newUsuario.getCorreo());
					model.put("imageUser", newUsuario.getGenero() == EGenero.FEMENINO.ordinal()
							? "https://raw.githubusercontent.com/cmvb/eutanasia-v9/master/eutanasia/src/assets/images/emailTemplate/woman1.png"
							: "https://raw.githubusercontent.com/cmvb/eutanasia-v9/master/eutanasia/src/assets/images/emailTemplate/man1.png");
					String urlRuta = URL_VERIFICAR_CUENTA_NUEVA
							+ Util.encriptarPassword(newUsuario.getUsuario() + "|" + newUsuario.getPassword());
					try {
						model.put("resetUrl", new URL(urlRuta).toURI().toASCIIString());
					} catch (Exception e) {
						throw new ModelNotFoundException(e.getMessage());
					}
					mailDto.setModel(model);

					mailUtil.sendMail(mailDto, ConstantesValidaciones.TEMPLATE_MAIL_ACTIVATE_USER);
				}
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("eutanasia.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<UsuarioAutorTB>(newUsuario, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarUsuario")
	public ResponseEntity<UsuarioAutorTB> modificarUsuario(@RequestBody UsuarioAutorTB usuarioAutor) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_AUTOR_TB, usuarioAutor);
			// Filtrar por usuario
			UsuarioAutorTB filtroUsuario = new UsuarioAutorTB();
			filtroUsuario.setUsuario(usuarioAutor.getUsuario());
			filtroUsuario.setId(usuarioAutor.getId());
			List<UsuarioAutorTB> listaPorUsuario = usuariosService.consultarUsuariosPorFiltros(filtroUsuario);
			if (listaPorUsuario != null && !listaPorUsuario.isEmpty()
					&& listaPorUsuario.get(0).getId() != usuarioAutor.getId()) {
				errores.add(ConstantesValidaciones.MSG_USUARIO_REPETIDO);
			}

			// Filtrar por correo
			UsuarioAutorTB filtroCorreo = new UsuarioAutorTB();
			filtroCorreo.setCorreo(usuarioAutor.getCorreo());
			filtroCorreo.setId(usuarioAutor.getId());
			List<UsuarioAutorTB> listaPorCorreo = usuariosService.consultarUsuariosPorFiltros(filtroCorreo);
			if (listaPorCorreo != null && !listaPorCorreo.isEmpty()
					&& listaPorUsuario.get(0).getId() != usuarioAutor.getId()) {
				errores.add(ConstantesValidaciones.MSG_CORREO_REPETIDO);
			}

			UsuarioAutorTB newUsuario = new UsuarioAutorTB();
			if (errores.isEmpty()) {
				usuarioAutor.setPassword(Util.encriptarPassword(usuarioAutor.getPassword()));
				newUsuario = usuariosService.modificarUsuario(usuarioAutor);
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("eutanasia.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<UsuarioAutorTB>(newUsuario, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
}

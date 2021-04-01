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
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.model.UsuarioAutorTB;
import com.project.cafe.CentralUsuarios.service.IRolService;
import com.project.cafe.CentralUsuarios.service.IUsuariosService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;
import com.project.cafe.CentralUsuarios.util.UtilMail;

@RestController
@RequestMapping("/central/rol")
public class ControladorRestRol {

	@Autowired
	private IRolService rolService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearRol")
	public ResponseEntity<RolTB> crearUsuario(@RequestBody RolTB rol) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_USUARIO_AUTOR_TB, rol);

			RolTB newRol= new RolTB();
			if (errores.isEmpty()) {
				newRol = rolService.crearRol(rol);
				
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("eutanasia.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<RolTB>(newRol, HttpStatus.OK);
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

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

import com.project.cafe.CentralUsuarios.dto.RequestConsultarRolesDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.RolTB;
import com.project.cafe.CentralUsuarios.service.IRolService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

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
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_ROL_TB, rol);

			RolTB newRol = new RolTB();
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarRolUnicoCrear(rol.getCodigo())) {
					newRol = rolService.crearRol(rol);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_ROL_REPETIDO;

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

			return new ResponseEntity<RolTB>(newRol, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarRolUnicoCrear(String codigo) {
		List<RolTB> listaRoles = rolService.buscarRolPorCodigo(codigo);
		if (listaRoles == null || listaRoles.isEmpty()) {
			return true;
		}
		return false;
	}

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarRol")
	public ResponseEntity<RolTB> modificarRol(@RequestBody RolTB rol) {
		try {
			RolTB newRol = new RolTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_ROL_TB, rol);
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarRolUnicoEditar(rol.getCodigo(), rol.getId())) {
					newRol = rolService.modificarRol(rol);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_ROL_REPETIDO;

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
			return new ResponseEntity<RolTB>(newRol, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}

	private boolean validarRolUnicoEditar(String codigo, long id) {
		List<RolTB> listaRol = rolService.buscarRolPorCodigo(codigo);
		if (listaRol == null || listaRol.isEmpty()) {
			return true;
		} else {
			if (listaRol.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarRolFiltros")
	public ResponseConsultarDTO<RolTB> consultarRolFiltros(@RequestBody RequestConsultarRolesDTO request) {
		try {
			return rolService.consultarRolesFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

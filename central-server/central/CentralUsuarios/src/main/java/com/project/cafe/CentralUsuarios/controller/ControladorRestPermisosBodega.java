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

import com.project.cafe.CentralUsuarios.dto.RequestBuscarPermisosBodegaUsuarioDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarPermisosBodegaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.PermisosBodegaTB;
import com.project.cafe.CentralUsuarios.service.IPermisosBodegaService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/permisosBodega")
public class ControladorRestPermisosBodega {

	@Autowired
	private IPermisosBodegaService permisosBodegaService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearPermisosBodega")
	public ResponseEntity<PermisosBodegaTB> crearPermisosBodega(@RequestBody PermisosBodegaTB permisosBodega) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_PERMISOS_BODEGA_TB, permisosBodega);

			PermisosBodegaTB nuevoPermisos = new PermisosBodegaTB();
			if (errores.isEmpty()) {
				// validar caja unico
				if (validarPermisosBodegaUnicoCrear(permisosBodega.getBodega().getId(),permisosBodega.getUsuario().getId())) {
					nuevoPermisos = permisosBodegaService.crearPermisosBodega(permisosBodega);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_PERMISOS_BODEGA_REPETIDO;

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

			return new ResponseEntity<PermisosBodegaTB>(nuevoPermisos, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarPermisosBodegaUnicoCrear(long idBodega, long idUsuario) {
		List<PermisosBodegaTB> listaPermisos = permisosBodegaService.buscarPermisosPorBodegaUsuario(idBodega, idUsuario);
		if (listaPermisos == null || listaPermisos.isEmpty()) {
			return true;
		}
		return false;
	}

	

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarPermisosBodega")
	public ResponseEntity<PermisosBodegaTB> modificarPermisosBodega(@RequestBody PermisosBodegaTB permisosBodega) {
		try {
			PermisosBodegaTB nuevoPermisos = new PermisosBodegaTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_PERMISOS_BODEGA_TB, permisosBodega);
			if (errores.isEmpty()) {
				// validar caja unico
				if (validarPrermisosBodegaUnicoEditar(permisosBodega.getBodega().getId(),permisosBodega.getUsuario().getId(),permisosBodega.getId())) {
					nuevoPermisos = permisosBodegaService.modificarPermisosBodega(permisosBodega);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_PERMISOS_BODEGA_REPETIDO;

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
			return new ResponseEntity<PermisosBodegaTB>(nuevoPermisos, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}

	private boolean validarPrermisosBodegaUnicoEditar(long idBodega, long idUsuario, long idPermisos) {
		List<PermisosBodegaTB> listaPermisos = permisosBodegaService.buscarPermisosPorBodegaUsuario(idBodega, idUsuario);
		if (listaPermisos == null || listaPermisos.isEmpty()) {
			return true;
		} else {
			if (listaPermisos.get(0).getId() == (idPermisos)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarPermisosBodegaFiltros")
	public ResponseConsultarDTO<PermisosBodegaTB> consultarPermisosBodegaFiltros(@RequestBody RequestConsultarPermisosBodegaDTO request) {
		try {
			return permisosBodegaService.consultarPermisosBodegaFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// CONSULTA

		@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		@RequestMapping("/consultarPermisosUsuarioBodega")
		public List<PermisosBodegaTB> consultarPermisosUsuarioBodega(@RequestBody RequestBuscarPermisosBodegaUsuarioDTO request) {
			try {
				return permisosBodegaService.consultarPermisosUsuarioBodega(request);
			} catch (JSONException e) {
				throw new ModelNotFoundException(e.getMessage());
			}
		}
	
}

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

import com.project.cafe.CentralUsuarios.dto.RequestConsultarCajasDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarPorIDDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarProyectosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.model.ProyectoTB;
import com.project.cafe.CentralUsuarios.service.ICajaService;
import com.project.cafe.CentralUsuarios.service.IProyectoService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/proyecto")
public class ControladorRestProyecto {

	@Autowired
	private IProyectoService proyectoService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearProyecto")
	public ResponseEntity<ProyectoTB> crearProyecto(@RequestBody ProyectoTB proyecto) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_PROYECTO_TB, proyecto);

			ProyectoTB nuevoProyecto = new ProyectoTB();
			if (errores.isEmpty()) {
				// validar caja unico
				if (validarProyectoUnicoCrear(proyecto.getNombre(),proyecto.getSociedad().getId())) {
					nuevoProyecto = proyectoService.crearProyecto(proyecto);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_PROYECTO_REPETIDO;

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

			return new ResponseEntity<ProyectoTB>(nuevoProyecto, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarProyectoUnicoCrear(String nombreProyecto, long id) {
		List<ProyectoTB> listaProyectos = proyectoService.buscarProyectoPorNombreSociedad(nombreProyecto, id);
		if (listaProyectos == null || listaProyectos.isEmpty()) {
			return true;
		}
		return false;
	}

	

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarProyecto")
	public ResponseEntity<ProyectoTB> modificarProyecto(@RequestBody ProyectoTB proyecto) {
		try {
			ProyectoTB nuevoProyecto = new ProyectoTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_PROYECTO_TB, proyecto);
			if (errores.isEmpty()) {
				// validar caja unico
				if (validarProyectoUnicoEditar(proyecto.getNombre(), proyecto.getSociedad().getId(),proyecto.getId())) {
					nuevoProyecto = proyectoService.modificarProyecto(proyecto);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_PROYECTO_REPETIDO;

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
			return new ResponseEntity<ProyectoTB>(nuevoProyecto, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}

	private boolean validarProyectoUnicoEditar(String nombreProyecto, long idSociedad, long idProyecto) {
		List<ProyectoTB> listaProyectos = proyectoService.buscarProyectoPorNombreSociedad(nombreProyecto, idSociedad);
		if (listaProyectos == null || listaProyectos.isEmpty()) {
			return true;
		} else {
			if (listaProyectos.get(0).getId() == (idProyecto)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarProyectoFiltros")
	public ResponseConsultarDTO<ProyectoTB> consultarProyectoFiltros(@RequestBody RequestConsultarProyectosDTO request) {
		try {
			return proyectoService.consultarProyectosFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// CONSULTA

		@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		@RequestMapping("/consultarProyectosPorSociedad")
		public List<ProyectoTB> consultarProyectosPorSociedad(@RequestBody RequestConsultarPorIDDTO request) {
			try {
				return proyectoService.consultarProyectosPorSociedad(request.getId());
			} catch (JSONException e) {
				throw new ModelNotFoundException(e.getMessage());
			}
		}
	
}

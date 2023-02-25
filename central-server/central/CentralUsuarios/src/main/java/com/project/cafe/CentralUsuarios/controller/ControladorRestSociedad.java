package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarSociedadActivosDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarSociedadDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.SociedadTB;
import com.project.cafe.CentralUsuarios.service.ISociedadService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/sociedad")
public class ControladorRestSociedad {

	@Autowired
	private ISociedadService sociedadService;

	// CONSULTA
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearSociedad")
	public ResponseEntity<SociedadTB> crearSociedad(@RequestBody SociedadTB sociedad) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_SOCIEDAD_TB, sociedad);

			SociedadTB newSoc = new SociedadTB();
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarSociedadUnicoCrear(sociedad.getNombre())) {
					sociedad.setQuienFacturar(0l);
					newSoc = sociedadService.crearSociedad(sociedad);
					newSoc.setQuienFacturar(newSoc.getId());
					newSoc = sociedadService.modificarSociedad(sociedad);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.SOCIEDAD_REPETIDO;

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

			return new ResponseEntity<SociedadTB>(newSoc, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarSociedadUnicoCrear(String nombre) {
		List<SociedadTB> listaSociedad = sociedadService.buscarRolPorNombre(nombre);
		if (listaSociedad == null || listaSociedad.isEmpty()) {
			return true;
		}
		return false;
	}

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarSociedad")
	public ResponseEntity<SociedadTB> modificarSociedad(@RequestBody SociedadTB sociedad) {
		try {
			SociedadTB newSociedad= new SociedadTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_SOCIEDAD_TB, sociedad);
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarSociedadUnicaEditar(sociedad.getNombre(), sociedad.getId())) {
					newSociedad = sociedadService.modificarSociedad(sociedad);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.SOCIEDAD_REPETIDO;

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
			return new ResponseEntity<SociedadTB>(newSociedad, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}

	private boolean validarSociedadUnicaEditar(String nombre, long id) {
		List<SociedadTB> listaSocieadad = sociedadService.buscarRolPorNombre(nombre);
		if (listaSocieadad == null || listaSocieadad.isEmpty()) {
			return true;
		} else {
			if (listaSocieadad.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarSociedadFiltros")
	public ResponseConsultarDTO<SociedadTB> consultarRolFiltros(@RequestBody RequestConsultarSociedadDTO request) {
		try {
			return sociedadService.consultarSociedadFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarSociedadActiva")
	public List<SociedadTB> consultarSociedadActiva() {
		try {
			return sociedadService.buscarSociedadActiva();
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarSociedadActivaPorCliente")
	public List<SociedadTB> consultarSociedadActivaPorCliente(@RequestBody RequestConsultarSociedadActivosDTO request) {
		try {
			return sociedadService.consultarSociedadActivaPorCliente(request.getIdCliente());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

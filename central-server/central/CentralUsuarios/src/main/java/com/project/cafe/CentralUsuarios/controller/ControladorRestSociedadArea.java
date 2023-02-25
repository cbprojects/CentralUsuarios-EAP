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
import com.project.cafe.CentralUsuarios.dto.RequestConsultarAreasPorSociedadActivosDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarSociedadAreaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.model.SociedadAreaTB;
import com.project.cafe.CentralUsuarios.service.ISociedadAreaService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/SociedadArea")
public class ControladorRestSociedadArea {

	@Autowired
	private ISociedadAreaService sociedadAreaService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearSociedadArea")
	public ResponseEntity<SociedadAreaTB> crearSociedadArea(@RequestBody SociedadAreaTB sociedadArea) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_SOCIEDAD_AREA_TB, sociedadArea);

			SociedadAreaTB newSociedadArea = new SociedadAreaTB();
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarSociedadAreaUnicoCrear(sociedadArea.getSociedad().getId(), sociedadArea.getArea().getId())) {
					newSociedadArea = sociedadAreaService.crearSociedadArea(newSociedadArea);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.SOCIEDAD_AREA_REPETIDO;

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

			return new ResponseEntity<SociedadAreaTB>(newSociedadArea, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarSociedadAreaUnicoCrear(long idS, long idA) {
		List<SociedadAreaTB> listaSociedadArea = sociedadAreaService.buscarSociedadAreaPorId(idS,idA);
		if (listaSociedadArea == null || listaSociedadArea.isEmpty()) {
			return true;
		}
		return false;
	}

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarSociedadArea")
	public ResponseEntity<SociedadAreaTB> modificarSociedadArea(@RequestBody SociedadAreaTB sociedadArea) {
		try {
			SociedadAreaTB newSociedadArea = new SociedadAreaTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_SOCIEDAD_AREA_TB, sociedadArea);
			if (errores.isEmpty()) {
				// validar rol unico
				if (validarSociedadAreaUnicoEditar(sociedadArea.getSociedad().getId(), sociedadArea.getArea().getId(),sociedadArea.getId())) {
					newSociedadArea = sociedadAreaService.modificarSociedadArea(newSociedadArea);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.SOCIEDAD_AREA_REPETIDO;

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
			return new ResponseEntity<SociedadAreaTB>(newSociedadArea, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}

	private boolean validarSociedadAreaUnicoEditar(long idS, long idA,long id) {
		List<SociedadAreaTB> listaSociedadArea = sociedadAreaService.buscarSociedadAreaPorId(idS,idA);
		if (listaSociedadArea == null || listaSociedadArea.isEmpty()) {
			return true;
		} else {
			if (listaSociedadArea.get(0).getId() == (id)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarSociedadAreaFiltros")
	public ResponseConsultarDTO<SociedadAreaTB> consultarSociedadAreaFiltros(@RequestBody RequestConsultarSociedadAreaDTO request) {
		try {
			return sociedadAreaService.consultarRolesFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// CONSULTA

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarAreasActivasPorSociedad")
	public List<AreaTB> buscarAreasActivasPorSociedad(@RequestBody RequestConsultarAreasPorSociedadActivosDTO request) {
		try {
			return sociedadAreaService.buscarAreasActivasPorSociedad(request.getId());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

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
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUnidadDocumentalDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;
import com.project.cafe.CentralUsuarios.service.ICajaService;
import com.project.cafe.CentralUsuarios.service.IUnidadDocumentalService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/unidadDocumental")
public class ControladorRestUnidadDocumental {

	@Autowired
	private ICajaService cajalService;

	@Autowired
	private IUnidadDocumentalService unidadDocumentalService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearUnidadDocumental")
	public ResponseEntity<UnidadDocumentalTB> crearUnidadDocumental(@RequestBody UnidadDocumentalTB unidadDocumental) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_UNIDAD_DOCUMENTAL_TB, unidadDocumental);

			UnidadDocumentalTB nuevaUnidadDocumental = new UnidadDocumentalTB();
			if (errores.isEmpty()) {
				// validar unidad documental unica por sociedad
				if (validarUnidadDocumentalUnicoCrear(unidadDocumental.getCodigo(),
						unidadDocumental.getSociedadArea().getSociedad().getId())) {
					CajaTB cajaInicial = cajalService
							.retornarCajaInicialPorSociedad(unidadDocumental.getSociedadArea().getSociedad());
					nuevaUnidadDocumental.setCaja(cajaInicial);
					nuevaUnidadDocumental = unidadDocumentalService.crearUnidadDocumental(nuevaUnidadDocumental);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_UNIDAD_DOCUMENTAL_REPETIDA;

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

			return new ResponseEntity<UnidadDocumentalTB>(nuevaUnidadDocumental, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarUnidadDocumentalUnicoCrear(String codigo, long id) {
		List<UnidadDocumentalTB> listaUnidadDocumental = unidadDocumentalService
				.buscarUnidadDocumentalPorCodigoSociedad(codigo, id);
		if (listaUnidadDocumental == null || listaUnidadDocumental.isEmpty()) {
			return true;
		}
		return false;
	}

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarUnidadDocumental")
	public ResponseEntity<UnidadDocumentalTB> editarUnidadDocumental(@RequestBody UnidadDocumentalTB unidadDocumental) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_UNIDAD_DOCUMENTAL_TB, unidadDocumental);

			UnidadDocumentalTB nuevaUnidadDocumental = new UnidadDocumentalTB();
			if (errores.isEmpty()) {
				// validar unidad documental unica por sociedad
				if (validarUnidadDocumentalUnicoEditar(unidadDocumental.getCodigo(),
						unidadDocumental.getSociedadArea().getSociedad().getId(),unidadDocumental.getId())) {
					if(unidadDocumental.getCaja().getCodigoAlterno().equals("CAJA_INICIAL")) {
						CajaTB cajaInicial = cajalService
								.retornarCajaInicialPorSociedad(unidadDocumental.getSociedadArea().getSociedad());
						nuevaUnidadDocumental.setCaja(cajaInicial);
					}
					nuevaUnidadDocumental = unidadDocumentalService.crearUnidadDocumental(nuevaUnidadDocumental);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_UNIDAD_DOCUMENTAL_REPETIDA;

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

			return new ResponseEntity<UnidadDocumentalTB>(nuevaUnidadDocumental, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarUnidadDocumentalUnicoEditar(String codigo, long idSociedad, long idCajaActual) {
		List<UnidadDocumentalTB> listaUnidadDocumental = unidadDocumentalService
				.buscarUnidadDocumentalPorCodigoSociedad(codigo, idSociedad);
		if (listaUnidadDocumental == null || listaUnidadDocumental.isEmpty()) {
			return true;
		} else {
			if (listaUnidadDocumental.get(0).getId() == (idCajaActual)) {
				return true;
			}
		}
		return false;
	}
// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUnidadDocumentalFiltros")
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalFiltros(@RequestBody RequestConsultarUnidadDocumentalDTO request) {
		try {
			return unidadDocumentalService.consultarUnidadDocumentalFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

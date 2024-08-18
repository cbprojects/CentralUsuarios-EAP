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
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.service.ICajaService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/caja")
public class ControladorRestCaja {

	@Autowired
	private ICajaService cajaService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearCaja")
	public ResponseEntity<CajaTB> crearCaja(@RequestBody CajaTB caja) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_CAJA_TB, caja);

			CajaTB nuevaCaja = new CajaTB();
			if (errores.isEmpty()) {
				// validar caja unico
				if (validarCajaUnicoCrear(caja.getCodigoAlterno(),caja.getCliente().getId())) {
					nuevaCaja = cajaService.crearCaja(caja);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_CAJA_REPETIDA;

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

			return new ResponseEntity<CajaTB>(nuevaCaja, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	private boolean validarCajaUnicoCrear(String codigoAlterno, long id) {
		List<CajaTB> listaCajas = cajaService.buscarcajaPorCodigoCliente(codigoAlterno, id);
		if (listaCajas == null || listaCajas.isEmpty()) {
			return true;
		}
		return false;
	}

	

	// UPDATE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/modificarCaja")
	public ResponseEntity<CajaTB> modificarCaja(@RequestBody CajaTB caja) {
		try {
			CajaTB nuevaCaja = new CajaTB();
			// validaciones de campos vacios o valores incorrectos
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_CAJA_TB, caja);
			if (errores.isEmpty()) {
				// validar caja unico
				if (validarCajaUnicoEditar(caja.getCodigoAlterno(), caja.getCliente().getId(),caja.getId())) {
					nuevaCaja = cajaService.modificarCaja(caja);
				} else {
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
					String mensajeErrores = ConstantesValidaciones.MSG_CAJA_REPETIDA;

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
			return new ResponseEntity<CajaTB>(nuevaCaja, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}

	}

	private boolean validarCajaUnicoEditar(String codigoAlterno, long idCliente, long idCajaActual) {
		List<CajaTB> listaCaja = cajaService.buscarcajaPorCodigoCliente(codigoAlterno, idCliente);
		if (listaCaja == null || listaCaja.isEmpty()) {
			return true;
		} else {
			if (listaCaja.get(0).getId() == (idCajaActual)) {
				return true;
			}
		}
		return false;
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarCajaFiltros")
	public ResponseConsultarDTO<CajaTB> consultarCajaFiltros(@RequestBody RequestConsultarCajasDTO request) {
		try {
			return cajaService.consultarCajasFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// CONSULTA

		@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		@RequestMapping("/consultarCajasPorCliente")
		public List<CajaTB> consultarCajasPorCliente(@RequestBody RequestConsultarPorIDDTO request) {
			try {
				return cajaService.consultarCajasPorCliente(request.getId());
			} catch (JSONException e) {
				throw new ModelNotFoundException(e.getMessage());
			}
		}
	
}

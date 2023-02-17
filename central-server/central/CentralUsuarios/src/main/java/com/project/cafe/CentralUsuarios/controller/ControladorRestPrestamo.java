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

import com.project.cafe.CentralUsuarios.dto.RequestConsultarPrestamoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestEditarPrestamoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarPrestamoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseMensajeCodigoDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.PrestamoHistoricoTB;
import com.project.cafe.CentralUsuarios.model.PrestamoTB;
import com.project.cafe.CentralUsuarios.service.IPrestamoService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/prestamo")
public class ControladorRestPrestamo {

	@Autowired
	private IPrestamoService prestamoService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/editarPrestamo")
	public ResponseEntity<ResponseMensajeCodigoDTO> editarPrestamo(@RequestBody RequestEditarPrestamoDTO request) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_PRESTAMO_TB, request);

			ResponseMensajeCodigoDTO respuesta = new ResponseMensajeCodigoDTO();
			if (errores.isEmpty()) {
				if(request.getEsCrear()) {
					List<PrestamoTB> lstPrestamo = prestamoService.buscarPrestamoVigente(request.getIdUd());
					if(lstPrestamo.isEmpty()) {
						PrestamoTB newPres=prestamoService.crearPrestamo(request);
						if(newPres!=null) {
							respuesta.setCodigo("0");
							respuesta.setMensaje("Prestamo Creado Satisfactoriamente");
						}else {
							respuesta.setCodigo("1");
							respuesta.setMensaje("Error al momento de generar el prestamo");
						}
					}else {
						respuesta.setCodigo("1");
						respuesta.setMensaje("Error esta unidad documental ya se encuentra prestada");
					}
				}else {
					try {
						prestamoService.eliminarPrestamo(request);
						respuesta.setCodigo("0");
						respuesta.setMensaje("Prestamo Devuelto Satisfactoriamente");
					} catch (Exception e) {
						respuesta.setCodigo("1");
						respuesta.setMensaje("Error al momento de devolver el prestamo");
					}
				}
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<ResponseMensajeCodigoDTO>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarPrestamo")
	public ResponseEntity<ResponseConsultarPrestamoDTO> consultarPrestamo(
			@RequestBody RequestConsultarPrestamoDTO request) {
		try {
			ResponseConsultarPrestamoDTO dataPrestamo = new ResponseConsultarPrestamoDTO();
			if (request != null && request.getIdUd() != null && request.getIdUd() != 0L) {
				List<PrestamoTB> lstPrestamo = prestamoService.buscarPrestamoVigente(request.getIdUd());
				List<PrestamoHistoricoTB> lstPrestamoHistorico = prestamoService
						.buscarPrestamoHistorico(request.getIdUd());
				if (lstPrestamo != null && !lstPrestamo.isEmpty()) {
					dataPrestamo.setTienePrestamo(true);
					dataPrestamo.setPrestamo(lstPrestamo.get(0));
				} else {
					dataPrestamo.setTienePrestamo(false);
				}
				dataPrestamo.setListaPrestamo(lstPrestamoHistorico);
			} else {
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");
				String mensajeErrores = ConstantesValidaciones.MSG_PRESTAMO_IDFALTANTE;

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<ResponseConsultarPrestamoDTO>(dataPrestamo, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

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

import com.project.cafe.CentralUsuarios.dto.ArchivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestAprobarRecepcionDTO;
import com.project.cafe.CentralUsuarios.dto.RequestCambiarCajaUnidadDocumentalMasivaDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarListaUdDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUDRecepcionDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUnidadDocumentalDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUnidadDocumentalMasivaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarUnidadDocumentalMasivaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseGenerarPdfDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseMensajeCodigoDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.model.SociedadAreaTB;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;
import com.project.cafe.CentralUsuarios.service.ICajaService;
import com.project.cafe.CentralUsuarios.service.ISociedadAreaService;
import com.project.cafe.CentralUsuarios.service.IUnidadDocumentalService;
import com.project.cafe.CentralUsuarios.util.ConstantesTablasNombre;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;

@RestController
@RequestMapping("/central/unidadDocumental")
public class ControladorRestUnidadDocumental {

	@Autowired
	private ICajaService cajalService;

	@Autowired
	private IUnidadDocumentalService unidadDocumentalService;

	@Autowired
	private ISociedadAreaService sociedadAreaService;

	// CREATE

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/crearUnidadDocumental")
	public ResponseEntity<UnidadDocumentalTB> crearUnidadDocumental(@RequestBody UnidadDocumentalTB unidadDocumental) {
		try {
			List<String> errores = Util.validaDatos(ConstantesTablasNombre.MRA_UNIDAD_DOCUMENTAL_TB, unidadDocumental);

			UnidadDocumentalTB nuevaUnidadDocumental = new UnidadDocumentalTB();
			if (errores.isEmpty()) {
				// Obtener cliente de sociedad
				CajaTB cajaInicial = cajalService
						.retornarCajaInicialPorCliente(unidadDocumental.getSociedadArea().getSociedad().getCliente());
				SociedadAreaTB sociedadArea = sociedadAreaService.buscarSociedadAreaPorSociedadArea(
						unidadDocumental.getSociedadArea().getSociedad().getId(),
						unidadDocumental.getSociedadArea().getArea().getId());
				unidadDocumental.setSociedadArea(sociedadArea);
				unidadDocumental.setCaja(cajaInicial);
				unidadDocumental.setRecepcionAprobada(false);
				nuevaUnidadDocumental = unidadDocumentalService.crearUnidadDocumental(unidadDocumental);

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

				if (!unidadDocumental.getCaja().getCodigoAlterno().equals("C-RECEP")) {
					CajaTB cajaInicial = cajalService
							.retornarCajaInicialPorCliente(unidadDocumental.getSociedadArea().getSociedad().getCliente());
					unidadDocumental.setCaja(cajaInicial);
				}

				SociedadAreaTB sociedadArea = sociedadAreaService.buscarSociedadAreaPorSociedadArea(
						unidadDocumental.getSociedadArea().getSociedad().getId(),
						unidadDocumental.getSociedadArea().getArea().getId());
				unidadDocumental.setSociedadArea(sociedadArea);

				nuevaUnidadDocumental = unidadDocumentalService.modificarUnidadDocumental(unidadDocumental);

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
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalFiltros(
			@RequestBody RequestConsultarUnidadDocumentalDTO request) {
		try {
			return unidadDocumentalService.consultarUnidadDocumentalFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
// CONSULTA RECEPCION
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUnidadDocumentalRecepcion")
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalRecepcion(
			@RequestBody RequestConsultarUDRecepcionDTO request) {
		try {
			return unidadDocumentalService.consultarUnidadDocumentalRecepcion(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUnidadDocumentalPorCajaMasiva")
	public ResponseConsultarUnidadDocumentalMasivaDTO consultarUnidadDocumentalPorCajaMasiva(
			@RequestBody RequestConsultarUnidadDocumentalMasivaDTO request) {
		try {
			ResponseConsultarUnidadDocumentalMasivaDTO response = new ResponseConsultarUnidadDocumentalMasivaDTO();
			response.setLstUnidadDocumentalCajaUno(
					unidadDocumentalService.RequestConsultarUnidadDocumentalPorCaja(request.getIdCajaUno()));
			response.setLstUnidadDocumentalCajaDos(
					unidadDocumentalService.RequestConsultarUnidadDocumentalPorCaja(request.getIdCajaDos()));
			return response;
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/cambiarCajaUnidadDocumentalMasiva")
	public ResponseEntity<ResponseMensajeCodigoDTO> cambiarCajaUnidadDocumentalMasivo(
			@RequestBody RequestCambiarCajaUnidadDocumentalMasivaDTO request) {
		try {
			if (request.getLstUnidadDocumentalCajaUno() != null) {
				if (!request.getLstUnidadDocumentalCajaUno().isEmpty()) {
					unidadDocumentalService.cambiarCajaUnidadDocumentalMasivo(request.getLstUnidadDocumentalCajaUno(),
							request.getIdCajaUno());
				}
			}
			if (request.getLstUnidadDocumentalCajaDos() != null) {
				if (!request.getLstUnidadDocumentalCajaDos().isEmpty()) {
					unidadDocumentalService.cambiarCajaUnidadDocumentalMasivo(request.getLstUnidadDocumentalCajaDos(),
							request.getIdCajaDos());
				}
			}
			ResponseMensajeCodigoDTO response = new ResponseMensajeCodigoDTO();
			response.setCodigo(PropertiesUtil.getProperty("centralusuarios.msg.validate.string.cero"));
			response.setMensaje(PropertiesUtil.getProperty("centralusuarios.msg.validate.string.mensaje.exito"));
			return new ResponseEntity<ResponseMensajeCodigoDTO>(response, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarUnidadDocumentalLista")
	public List<UnidadDocumentalTB> consultarUnidadDocumentalFiltros(
			@RequestBody RequestConsultarListaUdDTO request) {
		try {
			return unidadDocumentalService.consultarUnidadDocumentalList(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/aprobacionRecepcion")
	public ResponseMensajeCodigoDTO aprobacionRecepcion(
			@RequestBody RequestAprobarRecepcionDTO request) {
		try {
			return unidadDocumentalService.aprobacionRecepcion(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/generarPdf")
	public ResponseGenerarPdfDTO generarPdf(@RequestBody RequestConsultarUDRecepcionDTO request) {
		try {
			return unidadDocumentalService.generarPdf(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/enviarPdf")
	public ResponseMensajeCodigoDTO enviarPdf(@RequestBody RequestConsultarUDRecepcionDTO request) {
		try {
			return unidadDocumentalService.enviarPdf(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
}

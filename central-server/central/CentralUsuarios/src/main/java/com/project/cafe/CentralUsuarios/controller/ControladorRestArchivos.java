package com.project.cafe.CentralUsuarios.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.project.cafe.CentralUsuarios.dto.CajaListDTO;
import com.project.cafe.CentralUsuarios.dto.MailDTO;
import com.project.cafe.CentralUsuarios.dto.RequestAgregarArchivosDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarArchivoUdDTO;
import com.project.cafe.CentralUsuarios.dto.RequestSendEMailDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseSendEMailDTO;
import com.project.cafe.CentralUsuarios.enums.EDestinoArchivo;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.service.IArchivoService;
import com.project.cafe.CentralUsuarios.service.IUnidadDocumentalService;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;
import com.project.cafe.CentralUsuarios.util.UtilMail;

@RestController
@RequestMapping("/central/archivos")
public class ControladorRestArchivos {

	@Autowired
	private UtilMail mailUtil;

	@Autowired
	IArchivoService archivoService;
	
	@Autowired
	IUnidadDocumentalService unidadDocumentalService;

	// Guardar y transferir archivos SFTP
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/subirImagen")
	public ResponseEntity<List<String>> subirImagenes(@RequestBody RequestAgregarArchivosDTO archivoDto) {
		try {
			List<String> listaArchivosResponseDto = new ArrayList<>();
			List<String> errores = Util.validarArchivo(archivoDto);
			if (errores.isEmpty()) {
				listaArchivosResponseDto.addAll(archivoService.subirImagen(archivoDto));
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}
			
			
			return new ResponseEntity<List<String>>(listaArchivosResponseDto, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	// borrar archivos
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/borrarImagen")
	public ResponseEntity<List<String>> borrarImagenes(@RequestBody RequestAgregarArchivosDTO archivoDto) {
		try {
			List<String> listaArchivosResponseDto = new ArrayList<>();
			List<String> errores = Util.validarArchivoBorrar(archivoDto);
			if (errores.isEmpty()) {
				listaArchivosResponseDto.addAll(archivoService.borrarImagen(archivoDto));
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}
			return new ResponseEntity<List<String>>(listaArchivosResponseDto, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// Obtener archivos SFTP
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/obtenerArchivos")
	public ResponseEntity<List<CajaListDTO>> obtenerArchivos(@RequestBody RequestConsultarArchivoUdDTO request) {
		try {
			List<CajaListDTO> listaArchivosResponseDto = new ArrayList<>();
			List<String> errores = new ArrayList<>();
//			if (request.getIdUnidadDocumental() == 0) {
//				errores.add(ConstantesValidaciones.RUTA_ARCHIVO + ConstantesValidaciones.VALOR_VACIO);
//			}

			if (errores.isEmpty()) {
				listaArchivosResponseDto.addAll(unidadDocumentalService.obtenerArchivos(request));

			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<List<CajaListDTO>>(listaArchivosResponseDto, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
	// Obtener archivos SFTP
		@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		@RequestMapping("/descargarArchivos")
		public ResponseEntity<List<ArchivoDTO>> descargarArchivos(@RequestBody RequestAgregarArchivosDTO archivoDto) {
			try {
				List<ArchivoDTO> listaArchivosResponseDto = new ArrayList<>();
				List<String> errores = new ArrayList<>();
				if (archivoDto.getIdUnidadDocumental() == 0) {
					errores.add(ConstantesValidaciones.RUTA_ARCHIVO + ConstantesValidaciones.VALOR_VACIO);
				}

				if (errores.isEmpty()) {
					listaArchivosResponseDto.addAll(archivoService.obtenerArchivos(archivoDto));

				} else {
					StringBuilder mensajeErrores = new StringBuilder();
					String erroresTitle = PropertiesUtil.getProperty("centralusuarios.msg.validate.erroresEncontrados");

					for (String error : errores) {
						mensajeErrores.append(error + "|");
					}

					throw new ModelNotFoundException(erroresTitle + mensajeErrores);
				}

				return new ResponseEntity<List<ArchivoDTO>>(listaArchivosResponseDto, HttpStatus.OK);
			} catch (JSONException e) {
				throw new ModelNotFoundException(e.getMessage());
			}
		}

	// Enviar email con plantilla
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/enviarEmail")
	public ResponseEntity<ResponseSendEMailDTO> enviarEmail(@RequestBody RequestSendEMailDTO sendEMailDto) {
		try {
			ResponseSendEMailDTO responseEmail = new ResponseSendEMailDTO();
			responseEmail.setCorreosEnviados(new ArrayList<>());
			responseEmail.setCorreosNoEnviados(new ArrayList<>());

			List<String> errores = Util.validarMail(sendEMailDto);
			if (errores.isEmpty()) {
				for (String correoDestino : sendEMailDto.getPara()) {
					MailDTO mailDto = new MailDTO();
					mailDto.setFrom(sendEMailDto.getDesde());
					mailDto.setTo(correoDestino);
					mailDto.setSubject(sendEMailDto.getAsunto() + " - EUTANASIA WEB PAGE");
					mailDto.setModel(sendEMailDto.getMapaDeLista());

					try {
						mailUtil.sendMail(mailDto, ConstantesValidaciones.TEMPLATE_MAIL_CONTACTO_BANDA);
						responseEmail.getCorreosEnviados().add(correoDestino);
					} catch (Exception e) {
						responseEmail.getCorreosNoEnviados().add(correoDestino);
					}
				}

				if (!responseEmail.getCorreosEnviados().isEmpty()) {
					responseEmail.setExitoso(true);
				}
				if (responseEmail.getCorreosNoEnviados().isEmpty()) {
					responseEmail.setMensaje(ConstantesValidaciones.MSG_ENVIO_EMAIL_EXITOSO);
				} else {
					responseEmail.setMensaje(ConstantesValidaciones.MSG_ENVIO_EMAIL_EXITOSO_CON_EXCEPCIONES
							+ responseEmail.getCorreosNoEnviados().toString());
				}
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("eutanasia.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<ResponseSendEMailDTO>(responseEmail, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
}

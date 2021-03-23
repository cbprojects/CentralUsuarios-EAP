package com.project.cafe.CentralUsuarios.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.ArchivoDTO;
import com.project.cafe.CentralUsuarios.dto.MailDTO;
import com.project.cafe.CentralUsuarios.dto.RequestSendEMailDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseSendEMailDTO;
import com.project.cafe.CentralUsuarios.enums.EDestinoArchivo;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.service.IArchivoService;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;
import com.project.cafe.CentralUsuarios.util.PropertiesUtil;
import com.project.cafe.CentralUsuarios.util.Util;
import com.project.cafe.CentralUsuarios.util.UtilMail;

@RestController
@RequestMapping("/eutanasia/paratodos")
public class ControladorRestArchivos {

	@Value("${ruta.responder.comentario}")
	private String URL_RESPONDER_COMENTARIO;

	@Autowired
	private UtilMail mailUtil;

	@Autowired
	IArchivoService archivoService;

	// Guardar y transferir archivos SFTP
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/subirImagen")
	public ResponseEntity<ArchivoDTO> subirImagen(@RequestBody ArchivoDTO archivoDto) {
		try {
			ArchivoDTO archivoResponseDto = new ArchivoDTO();
			archivoDto.setRutaArchivo(archivoDto.getDestinoArchivo() == EDestinoArchivo.USER.ordinal()
					? ConstantesValidaciones.RUTA_SFTP_IMAGES_USUARIO
					: ConstantesValidaciones.RUTA_SFTP_IMAGES_POST);
			List<String> errores = Util.validarArchivo(archivoDto);
			if (errores.isEmpty()) {
				archivoResponseDto = archivoService.subirImagen(archivoDto);
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("eutanasia.msg.validate.erroresEncontrados");

				for (String error : errores) {
					mensajeErrores.append(error + "|");
				}

				throw new ModelNotFoundException(erroresTitle + mensajeErrores);
			}

			return new ResponseEntity<ArchivoDTO>(archivoResponseDto, HttpStatus.OK);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	// Obtener archivos SFTP
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/obtenerArchivos")
	public ResponseEntity<List<ArchivoDTO>> obtenerArchivos(@RequestBody ArchivoDTO archivoDto) {
		try {
			List<ArchivoDTO> listaArchivosResponseDto = new ArrayList<>();
			List<String> errores = new ArrayList<>();
			if (StringUtils.isBlank(archivoDto.getRutaArchivo())) {
				errores.add(ConstantesValidaciones.RUTA_ARCHIVO + ConstantesValidaciones.VALOR_VACIO);
			}

			if (errores.isEmpty()) {
				if (ConstantesValidaciones.RUTA_SFTP_IMAGES.equalsIgnoreCase(archivoDto.getRutaArchivo())) {
					listaArchivosResponseDto.addAll(archivoService.obtenerArchivos(
							ConstantesValidaciones.RUTA_SFTP_IMAGES_USUARIO, archivoDto.getNombreArchivo()));
					listaArchivosResponseDto.addAll(archivoService.obtenerArchivos(
							ConstantesValidaciones.RUTA_SFTP_IMAGES_POST, archivoDto.getNombreArchivo()));
				} else {
					listaArchivosResponseDto.addAll(
							archivoService.obtenerArchivos(archivoDto.getRutaArchivo(), archivoDto.getNombreArchivo()));
				}
			} else {
				StringBuilder mensajeErrores = new StringBuilder();
				String erroresTitle = PropertiesUtil.getProperty("eutanasia.msg.validate.erroresEncontrados");

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

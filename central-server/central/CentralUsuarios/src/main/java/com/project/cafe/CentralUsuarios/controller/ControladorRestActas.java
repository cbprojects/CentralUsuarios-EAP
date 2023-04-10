package com.project.cafe.CentralUsuarios.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.RequestAprobarRecepcionDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarActaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseMensajeCodigoDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.ActaTB;
import com.project.cafe.CentralUsuarios.service.IActaService;

@RestController
@RequestMapping("/central/acta")
public class ControladorRestActas {

	@Autowired
	private IActaService actaService;

	// CONSULTA

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarActaFiltros")
	public ResponseConsultarDTO<ActaTB> consultarActaFiltros(@RequestBody RequestConsultarActaDTO request) {
		try {
			return actaService.consultarActaFiltros(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/aprobacionActa")
	public ResponseMensajeCodigoDTO aprobacionActa(
			@RequestBody RequestAprobarRecepcionDTO request) {
		try {
			return actaService.aprobacionActa(request);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}
	
}

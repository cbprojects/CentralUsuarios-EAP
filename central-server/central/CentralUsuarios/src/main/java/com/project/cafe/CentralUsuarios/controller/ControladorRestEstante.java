package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarEstantesActivosDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.EstanteTB;
import com.project.cafe.CentralUsuarios.service.IEstanteService;

@RestController
@RequestMapping("/central/Estante")
public class ControladorRestEstante {

	@Autowired
	private IEstanteService estanteService;	

	// CONSULTA

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarEstantesActivosPorCuerpo")
	public List<EstanteTB> buscarEstantesActivosPorCuerpo(@RequestBody RequestConsultarEstantesActivosDTO request) {
		try {
			return estanteService.buscarEstantesActivosPorCuerpo(request.getIdCuerpo());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

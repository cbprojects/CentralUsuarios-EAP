package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarCuerposActivosDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.CuerpoTB;
import com.project.cafe.CentralUsuarios.service.ICuerpoService;

@RestController
@RequestMapping("/central/Cuerpo")
public class ControladorRestCuerpo {

	@Autowired
	private ICuerpoService cuerpoService;	

	// CONSULTA

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarCuerposActivosPorBloque")
	public List<CuerpoTB> buscarCuerposActivosPorBloque(@RequestBody RequestConsultarCuerposActivosDTO request) {
		try {
			return cuerpoService.buscarCuerposActivosPorBloque(request.getIdBloque());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

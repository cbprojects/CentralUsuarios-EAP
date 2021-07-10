package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarBodegasActivasDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.BodegaTB;
import com.project.cafe.CentralUsuarios.service.IBodegaService;

@RestController
@RequestMapping("/central/Bodega")
public class ControladorRestBodega {

	@Autowired
	private IBodegaService bodegaService;	

	// CONSULTA

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarBodegasActivasPorSede")
	public List<BodegaTB> buscarBodegasActivasPorSede(@RequestBody RequestConsultarBodegasActivasDTO request) {
		try {
			return bodegaService.buscarBodegasActivasPorSede(request.getIdSede());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

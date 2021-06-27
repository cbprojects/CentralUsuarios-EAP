package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarEntrepanosActivosDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.EntrepanoTB;
import com.project.cafe.CentralUsuarios.service.IEntrepanoService;

@RestController
@RequestMapping("/central/Entrepano")
public class ControladorRestEntrepano {

	@Autowired
	private IEntrepanoService entrepanoService;	

	// CONSULTA

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarEntrepanosActivosPorEstante")
	public List<EntrepanoTB> buscarEntrepanosActivosPorEstante(@RequestBody RequestConsultarEntrepanosActivosDTO request) {
		try {
			return entrepanoService.buscarEntrepanosActivosPorEstante(request.getIdEstante());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

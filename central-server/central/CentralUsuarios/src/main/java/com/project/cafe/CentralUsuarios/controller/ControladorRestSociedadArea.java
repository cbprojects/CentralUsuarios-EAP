package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarAreasPorSociedadActivosDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.service.ISociedadAreaService;

@RestController
@RequestMapping("/central/SociedadArea")
public class ControladorRestSociedadArea {

	@Autowired
	private ISociedadAreaService sociedadAreaService;	

	// CONSULTA

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarAreasActivasPorSociedad")
	public List<AreaTB> buscarAreasActivasPorSociedad(@RequestBody RequestConsultarAreasPorSociedadActivosDTO request) {
		try {
			return sociedadAreaService.buscarAreasActivasPorSociedad(request.getId());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

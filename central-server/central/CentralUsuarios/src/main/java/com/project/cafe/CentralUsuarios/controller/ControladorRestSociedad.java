package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.SociedadTB;
import com.project.cafe.CentralUsuarios.service.ISociedadService;

@RestController
@RequestMapping("/central/sociedad")
public class ControladorRestSociedad {

	@Autowired
	private ISociedadService sociedadService;

	// CONSULTA

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/consultarSociedadActiva")
	public List<SociedadTB> consultarSociedadActiva() {
		try {
			return sociedadService.buscarSociedadActiva();
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

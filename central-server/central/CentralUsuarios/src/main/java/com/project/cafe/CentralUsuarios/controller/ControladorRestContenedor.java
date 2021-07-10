package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.ContenedorUDTB;
import com.project.cafe.CentralUsuarios.service.IContenedorService;

@RestController
@RequestMapping("/central/Contenedor")
public class ControladorRestContenedor {
	
	@Autowired
	private IContenedorService contenedorService;	

	// CONSULTA

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarContenedoresActivos")
	public List<ContenedorUDTB> buscarContenedoresActivos() {
		try {
			return contenedorService.buscarContenedoresActivos();
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

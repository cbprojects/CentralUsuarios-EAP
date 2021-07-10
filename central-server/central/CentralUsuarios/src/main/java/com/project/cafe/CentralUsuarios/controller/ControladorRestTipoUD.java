package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.TipoUDTB;
import com.project.cafe.CentralUsuarios.service.ITipoUDService;

@RestController
@RequestMapping("/central/TipoUD")
public class ControladorRestTipoUD {

	@Autowired
	private ITipoUDService tipoUDService;	

	// CONSULTA

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarTipoUDActivos")
	public List<TipoUDTB> buscarUnidadDocumentalActivos() {
		try {
			return tipoUDService.buscarTipoUDActivos();
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

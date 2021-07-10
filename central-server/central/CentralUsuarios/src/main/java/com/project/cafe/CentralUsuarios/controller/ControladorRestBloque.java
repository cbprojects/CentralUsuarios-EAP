package com.project.cafe.CentralUsuarios.controller;

import java.util.List;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarBloquesActivosDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.BloqueTB;
import com.project.cafe.CentralUsuarios.service.IBloqueService;

@RestController
@RequestMapping("/central/Bloque")
public class ControladorRestBloque {

	@Autowired
	private IBloqueService bloqueService;	

	// CONSULTA

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarBloquesActivosPorBodega")
	public List<BloqueTB> buscarBloquesActivosPorBodega(@RequestBody RequestConsultarBloquesActivosDTO request) {
		try {
			return bloqueService.buscarBloquesActivosPorBodega(request.getIdBodega());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

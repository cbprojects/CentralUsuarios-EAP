package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarSedesActivasDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.service.IUsuarioSedeService;

@RestController
@RequestMapping("/central/UsuarioSede")
public class ControladorRestUsuarioSede {

	@Autowired
	private IUsuarioSedeService usuarioSedeService;	

	// CONSULTA

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarSedesActivasPorUsuario")
	public List<SedeTB> buscarSedesActivasPorUsuario(@RequestBody RequestConsultarSedesActivasDTO request) {
		try {
			return usuarioSedeService.buscarSedesActivasPorUsuario(request.getEmail());
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

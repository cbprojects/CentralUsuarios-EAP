package com.project.cafe.CentralUsuarios.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.service.IClienteService;

@RestController
@RequestMapping("/central/Cliente")
public class ControladorRestCliente {

	@Autowired
	private IClienteService clienteService;	

	// CONSULTA

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarClientesActivos")
	public List<ClienteTB> buscarClientesActivos() {
		try {
			return clienteService.buscarClientesActivos();
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

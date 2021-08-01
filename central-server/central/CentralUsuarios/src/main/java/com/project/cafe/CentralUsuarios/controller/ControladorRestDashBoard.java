package com.project.cafe.CentralUsuarios.controller;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarDashBoardDTO;
import com.project.cafe.CentralUsuarios.exception.ModelNotFoundException;
import com.project.cafe.CentralUsuarios.service.IDashboardService;

@RestController
@RequestMapping("/central/DashBoard")
public class ControladorRestDashBoard {

	@Autowired
	private IDashboardService dashboardService;

	// CONSULTA

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/buscarDashBoardPorPerfil/{idPerfil}")
	public RequestConsultarDashBoardDTO buscarDashBoardPorPerfil(@PathVariable("idPerfil") String idPerfil) {
		try {
			return dashboardService.cargarDashboard(idPerfil);
		} catch (JSONException e) {
			throw new ModelNotFoundException(e.getMessage());
		}
	}

}

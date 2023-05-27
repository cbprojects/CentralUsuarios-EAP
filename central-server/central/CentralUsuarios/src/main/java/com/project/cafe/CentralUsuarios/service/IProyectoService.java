package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarProyectosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ProyectoTB;

public interface IProyectoService {


	ProyectoTB crearProyecto(ProyectoTB proyecto);

	ProyectoTB modificarProyecto(ProyectoTB proyecto);
	
	List<ProyectoTB> buscarProyectoPorNombreSociedad(String nombreProyecto, long id);

	ResponseConsultarDTO<ProyectoTB> consultarProyectosFiltros(RequestConsultarProyectosDTO request);

	List<ProyectoTB> consultarProyectosPorSociedad(Long id);

}

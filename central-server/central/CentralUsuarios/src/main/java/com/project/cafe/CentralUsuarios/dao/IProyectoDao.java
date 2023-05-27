package com.project.cafe.CentralUsuarios.dao;

import java.util.List;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarProyectosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ProyectoTB;

@EnableTransactionManagement
public interface IProyectoDao {

	ProyectoTB crearProyecto(ProyectoTB proyecto);

	ProyectoTB modificarProyecto(ProyectoTB proyecto);

	List<ProyectoTB> buscarProyectoPorNombreSociedad(String nombreProyecto, long id);

	ResponseConsultarDTO<ProyectoTB> consultarProyectosFiltros(RequestConsultarProyectosDTO request);

	List<ProyectoTB> consultarProyectosPorSociedad(Long id);
	
}

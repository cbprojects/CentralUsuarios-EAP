package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IProyectoDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarProyectosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ProyectoTB;
import com.project.cafe.CentralUsuarios.service.IProyectoService;

@Service
public class ProyectoServiceImpl implements IProyectoService {

	@Autowired
	private IProyectoDao proyectoDao;
	
	

	@Transactional
	@Override
	public ProyectoTB crearProyecto(ProyectoTB proyecto) {
		return proyectoDao.crearProyecto(proyecto);
	}

	@Transactional
	@Override
	public ProyectoTB modificarProyecto(ProyectoTB proyecto) {
		return proyectoDao.modificarProyecto(proyecto);
	}

	@Override
	public List<ProyectoTB> buscarProyectoPorNombreSociedad(String nombreProyecto, long id) {
		return proyectoDao.buscarProyectoPorNombreSociedad(nombreProyecto,id);
	}

	@Override
	public ResponseConsultarDTO<ProyectoTB> consultarProyectosFiltros(RequestConsultarProyectosDTO request) {
		return proyectoDao.consultarProyectosFiltros(request);
	}

	@Override
	public List<ProyectoTB> consultarProyectosPorSociedad(Long id) {
		return proyectoDao.consultarProyectosPorSociedad(id);
	}
	
		
	
	

}

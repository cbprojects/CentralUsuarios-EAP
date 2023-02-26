package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ISociedadDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarSociedadDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SociedadTB;
import com.project.cafe.CentralUsuarios.service.ISociedadService;

@Service
public class SociedadServiceImpl implements ISociedadService {

	@Autowired
	private ISociedadDao sociedadDAO;

	
	@Override
	public List<SociedadTB> buscarSociedadActiva() {
		return sociedadDAO.buscarSociedadActiva();
	}


	@Override
	public SociedadTB crearSociedad(SociedadTB sociedad) {
		return sociedadDAO.crearSociedad(sociedad);
	}


	@Override
	@Transactional
	public SociedadTB modificarSociedad(SociedadTB sociedad) {
		return sociedadDAO.modificarSociedad(sociedad);
	}


	@Override
	public List<SociedadTB> buscarRolPorNombre(String nombre) {
		return sociedadDAO.buscarRolPorNombre(nombre);
	}


	@Override
	public ResponseConsultarDTO<SociedadTB> consultarSociedadFiltros(RequestConsultarSociedadDTO request) {
		return sociedadDAO.consultarSociedadFiltros(request);
	}


	@Override
	public List<SociedadTB> consultarSociedadActivaPorCliente(Long idCliente) {
		return sociedadDAO.consultarSociedadActivaPorCliente(idCliente);
	}
	
}

package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ISociedadAreaDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarSociedadAreaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.model.SociedadAreaTB;
import com.project.cafe.CentralUsuarios.service.ISociedadAreaService;

@Service
public class SociedadAreaServiceImpl implements ISociedadAreaService {

	@Autowired
	private ISociedadAreaDao sociedadAreaDao;

	@Override
	public List<AreaTB> buscarAreasActivasPorSociedad(Long idSociedad) {
		return sociedadAreaDao.buscarAreasActivasPorSociedad(idSociedad);
	}

	@Override
	public SociedadAreaTB buscarSociedadAreaPorSociedadArea(long idSociedad, long idArea) {
		List<SociedadAreaTB> lstSociedadArea= sociedadAreaDao.buscarSociedadAreaPorSociedadArea(idSociedad,idArea);
		return lstSociedadArea.get(0);
	}

	@Override
	public SociedadAreaTB crearSociedadArea(SociedadAreaTB newSociedadArea) {
		return sociedadAreaDao.crearSociedadArea(newSociedadArea);
	}

	@Override
	public SociedadAreaTB modificarSociedadArea(SociedadAreaTB newSociedadArea) {
		return sociedadAreaDao.modificarSociedadArea(newSociedadArea);
	}

	@Override
	public ResponseConsultarDTO<SociedadAreaTB> consultarRolesFiltros(RequestConsultarSociedadAreaDTO request) {
		return sociedadAreaDao.consultarRolesFiltros(request);
	}

	@Override
	public List<SociedadAreaTB> buscarSociedadAreaPorId(long idS, long idA) {
		return sociedadAreaDao.buscarSociedadAreaPorId(idS,idA);
	}

}

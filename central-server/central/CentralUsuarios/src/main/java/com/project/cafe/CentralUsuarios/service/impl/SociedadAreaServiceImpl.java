package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ISociedadAreaDao;
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

}

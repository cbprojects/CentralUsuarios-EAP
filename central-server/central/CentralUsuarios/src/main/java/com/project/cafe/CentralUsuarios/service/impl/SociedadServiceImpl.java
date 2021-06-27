package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ISociedadDao;
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
	
}

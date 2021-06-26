package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IBodegaDao;
import com.project.cafe.CentralUsuarios.model.BodegaTB;
import com.project.cafe.CentralUsuarios.service.IBodegaService;

@Service
public class BodegaServiceImpl implements IBodegaService {

	@Autowired
	private IBodegaDao bodegaDao;
	
	@Override
	public List<BodegaTB> buscarBodegasActivasPorSede(Long idSede) {
		return bodegaDao.buscarBodegasActivasPorSede(idSede);
	}

}

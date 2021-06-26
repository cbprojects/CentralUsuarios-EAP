package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ICuerpoDao;
import com.project.cafe.CentralUsuarios.model.CuerpoTB;
import com.project.cafe.CentralUsuarios.service.ICuerpoService;

@Service
public class CuerpoServiceImpl implements ICuerpoService {

	@Autowired
	private ICuerpoDao cuerpoDao;
	
	@Override
	public List<CuerpoTB> buscarCuerposActivosPorBloque(Long idBloque) {
		return cuerpoDao.buscarCuerposActivosPorBloque(idBloque);
	}

}

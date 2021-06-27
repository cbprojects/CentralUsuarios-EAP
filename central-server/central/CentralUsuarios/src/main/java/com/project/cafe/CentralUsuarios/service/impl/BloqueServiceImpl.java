package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IBloqueDao;
import com.project.cafe.CentralUsuarios.model.BloqueTB;
import com.project.cafe.CentralUsuarios.service.IBloqueService;

@Service
public class BloqueServiceImpl implements IBloqueService {

	@Autowired
	private IBloqueDao bloqueDao;
	
	@Override
	public List<BloqueTB> buscarBloquesActivosPorBodega(Long idBodega) {
		return bloqueDao.buscarBloquesActivosPorBodega(idBodega);
	}

}

package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IEntrepanoDao;
import com.project.cafe.CentralUsuarios.model.EntrepanoTB;
import com.project.cafe.CentralUsuarios.service.IEntrepanoService;

@Service
public class EntrepanoServiceImpl implements IEntrepanoService {

	@Autowired
	private IEntrepanoDao entrepanoDao;
	
	@Override
	public List<EntrepanoTB> buscarEntrepanosActivosPorEstante(Long idEstante) {
		return entrepanoDao.buscarEntrepanosActivosPorEstante(idEstante);
	}

}

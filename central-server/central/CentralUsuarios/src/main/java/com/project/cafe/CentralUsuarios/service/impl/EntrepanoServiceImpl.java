package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ICuerpoDao;
import com.project.cafe.CentralUsuarios.dao.IEntrepanoDao;
import com.project.cafe.CentralUsuarios.dao.IEstanteDao;
import com.project.cafe.CentralUsuarios.model.CuerpoTB;
import com.project.cafe.CentralUsuarios.model.EntrepanoTB;
import com.project.cafe.CentralUsuarios.model.EstanteTB;
import com.project.cafe.CentralUsuarios.service.ICuerpoService;
import com.project.cafe.CentralUsuarios.service.IEntrepanoService;
import com.project.cafe.CentralUsuarios.service.IEstanteService;

@Service
public class EntrepanoServiceImpl implements IEntrepanoService {

	@Autowired
	private IEntrepanoDao entrepanoDao;
	
	@Override
	public List<EntrepanoTB> buscarEntrepanosActivosPorEstante(Long idEstante) {
		return entrepanoDao.buscarEntrepanosActivosPorEstante(idEstante);
	}

}

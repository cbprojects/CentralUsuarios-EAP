package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IServidorDao;
import com.project.cafe.CentralUsuarios.model.ServidorTB;
import com.project.cafe.CentralUsuarios.service.IServidorService;

@Service
public class ServidorServiceImpl implements IServidorService {

	@Autowired
	private IServidorDao servidorDao;
	
	@Override
	public List<ServidorTB> consultarServidorActivo() {
		return servidorDao.consultarServidorActivo();
	}

}

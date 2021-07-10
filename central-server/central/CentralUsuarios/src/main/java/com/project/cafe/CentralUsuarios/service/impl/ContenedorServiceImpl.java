package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IContenedorDao;
import com.project.cafe.CentralUsuarios.dao.ITipoUDDao;
import com.project.cafe.CentralUsuarios.model.ContenedorUDTB;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;
import com.project.cafe.CentralUsuarios.service.IContenedorService;
import com.project.cafe.CentralUsuarios.service.ITipoUDService;

@Service
public class ContenedorServiceImpl implements IContenedorService {

	@Autowired
	private IContenedorDao contenedorDao;

	@Override
	public List<ContenedorUDTB> buscarContenedoresActivos() {
		return contenedorDao.buscarContenedoresActivos();
	}

}

package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ITipoUDDao;
import com.project.cafe.CentralUsuarios.model.TipoUDTB;
import com.project.cafe.CentralUsuarios.service.ITipoUDService;

@Service
public class TipoUDServiceImpl implements ITipoUDService {

	@Autowired
	private ITipoUDDao tipoUDDao;

	@Override
	public List<TipoUDTB> buscarTipoUDActivos() {
		return tipoUDDao.buscarTipoUDActivos();
	}

}

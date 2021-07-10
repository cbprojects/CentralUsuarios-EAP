package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IUnidadDocumentalDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUnidadDocumentalDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;
import com.project.cafe.CentralUsuarios.service.IUnidadDocumentalService;

@Service
public class UnidadDocumentalServiceImpl implements IUnidadDocumentalService {

	@Autowired
	private IUnidadDocumentalDao unidadDocumentalDAO;

	@Transactional
	@Override
	public UnidadDocumentalTB crearUnidadDocumental(UnidadDocumentalTB unidadDocumental) {
		return unidadDocumentalDAO.crearUnidadDocumental(unidadDocumental);
	}

	@Transactional
	@Override
	public UnidadDocumentalTB modificarUnidadDocumental(UnidadDocumentalTB unidadDocumental) {
		return unidadDocumentalDAO.modificarUnidadDocumental(unidadDocumental);
	}
	
	@Override
	public List<UnidadDocumentalTB> buscarUnidadDocumentalPorCodigoSociedad(String codigo, long idSociedad) {
		return unidadDocumentalDAO.buscarUnidadDocumentalPorCodigoSociedad(codigo,idSociedad);
	}

	@Override
	public ResponseConsultarDTO<UnidadDocumentalTB> consultarUnidadDocumentalFiltros(RequestConsultarUnidadDocumentalDTO filtroUnidadDocumental){
		// TODO Auto-generated method stub
		return unidadDocumentalDAO.consultarUnidadDocumentalFiltros(filtroUnidadDocumental);
	}
	

}

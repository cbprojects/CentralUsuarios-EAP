package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ICajaDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarCajasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.service.ICajaService;

@Service
public class CajaServiceImpl implements ICajaService {

	@Autowired
	private ICajaDao cajaDAO;

	@Transactional
	@Override
	public CajaTB crearCaja(CajaTB caja) {
		return cajaDAO.crearCaja(caja);
	}

	@Transactional
	@Override
	public CajaTB modificarCaja(CajaTB caja) {
		return cajaDAO.modificarCaja(caja);
	}
	
	@Override
	public List<CajaTB> buscarcajaPorCodigoSociedad(String codigocaja, long idSociedad) {
		return cajaDAO.buscarcajaPorCodigoSociedad(codigocaja,idSociedad);
	}

	@Override
	public ResponseConsultarDTO<CajaTB> consultarCajasFiltros(RequestConsultarCajasDTO filtroCaja) {
		// TODO Auto-generated method stub
		return cajaDAO.consultarCajasFiltros(filtroCaja);
	}
	

}

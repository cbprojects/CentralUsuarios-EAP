package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ICajaDao;
import com.project.cafe.CentralUsuarios.dao.IEntrepanoDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarCajasDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.model.EntrepanoTB;
import com.project.cafe.CentralUsuarios.model.SociedadTB;
import com.project.cafe.CentralUsuarios.service.ICajaService;

@Service
public class CajaServiceImpl implements ICajaService {

	@Autowired
	private ICajaDao cajaDAO;
	
	@Autowired
	private IEntrepanoDao entrepanoDAO;

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
		return cajaDAO.consultarCajasFiltros(filtroCaja);
	}

	@Transactional
	@Override
	public CajaTB retornarCajaInicialPorSociedad(SociedadTB sociedad) {
		List<CajaTB> lstCajas=cajaDAO.buscarcajaPorCodigoSociedad("C-INICIAL",sociedad.getId());
		if(lstCajas == null || lstCajas.isEmpty()) {
			List<EntrepanoTB> lstEntrepano = entrepanoDAO.buscarEntrepanoPorCodigo("ENTREPANO0");
			EntrepanoTB nuevoEntrepano= new EntrepanoTB();
			nuevoEntrepano=lstEntrepano.get(0);
			CajaTB nuevaCaja= new CajaTB();
			nuevaCaja.setSociedad(sociedad);
			nuevaCaja.setCodigoAlterno("C-INICIAL");
			nuevaCaja.setCodigoBarras("CAJA_INICIAL");
			nuevaCaja.setDescripcion("Caja inicial sociedad:"+sociedad.getNombre());
			nuevaCaja.setEntrepano(nuevoEntrepano);
			Short activo=1;
			nuevaCaja.setEstado(activo);
			nuevaCaja.setQr("CAJA_INICIAL");
			return cajaDAO.crearCaja(nuevaCaja);
		}else {
			return lstCajas.get(0);
		}
	}

	@Override
	public List<CajaTB> consultarCajasPorSociedad(Long idSociedad) {
		return cajaDAO.consultarCajasPorSociedad(idSociedad);
	}
	

}

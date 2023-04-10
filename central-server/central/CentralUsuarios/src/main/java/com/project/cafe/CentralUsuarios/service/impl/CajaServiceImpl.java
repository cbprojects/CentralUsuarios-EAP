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
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.model.EntrepanoTB;
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
	public List<CajaTB> buscarcajaPorCodigoCliente(String codigocaja, long idCliente) {
		return cajaDAO.buscarcajaPorCodigoCliente(codigocaja,idCliente);
	}

	@Override
	public ResponseConsultarDTO<CajaTB> consultarCajasFiltros(RequestConsultarCajasDTO filtroCaja) {
		return cajaDAO.consultarCajasFiltros(filtroCaja);
	}

	@Transactional
	@Override
	public CajaTB retornarCajaInicialPorCliente(ClienteTB cliente) {
		List<CajaTB> lstCajas=cajaDAO.buscarcajaPorCodigoCliente("C-RECEP",cliente.getId());
		if(lstCajas == null || lstCajas.isEmpty()) {
			List<EntrepanoTB> lstEntrepano = entrepanoDAO.buscarEntrepanoPorCodigo("ENTREPANO0");
			EntrepanoTB nuevoEntrepano= new EntrepanoTB();
			nuevoEntrepano=lstEntrepano.get(0);
			CajaTB nuevaCaja= new CajaTB();
			nuevaCaja.setCliente(cliente);
			nuevaCaja.setCodigoAlterno("C-RECEP");
			nuevaCaja.setCodigoBarras("CAJA_RECEPCION");
			nuevaCaja.setDescripcion("Caja recepcion cliente:"+cliente.getNombre());
			nuevaCaja.setEntrepano(nuevoEntrepano);
			Short activo=1;
			nuevaCaja.setEstado(activo);
			nuevaCaja.setQr("C-RECEP");
			nuevaCaja.setUsuarioCreacion("SYSTEM");
			nuevaCaja.setUsuarioActualizacion("SYSTEM");
			return cajaDAO.crearCaja(nuevaCaja);
		}else {
			return lstCajas.get(0);
		}
	}

	@Override
	public List<CajaTB> consultarCajasPorCliente(Long idCliente) {
		return cajaDAO.consultarCajasPorCliente(idCliente);
	}
	
	@Transactional
	@Override
	public CajaTB retornarCajaPrimeraPorCliente(ClienteTB cliente) {
		List<CajaTB> lstCajas=cajaDAO.buscarcajaPorCodigoCliente("C-INICIAL",cliente.getId());
		if(lstCajas == null || lstCajas.isEmpty()) {
			List<EntrepanoTB> lstEntrepano = entrepanoDAO.buscarEntrepanoPorCodigo("ENTREPANO0");
			EntrepanoTB nuevoEntrepano= new EntrepanoTB();
			nuevoEntrepano=lstEntrepano.get(0);
			CajaTB nuevaCaja= new CajaTB();
			nuevaCaja.setCliente(cliente);
			nuevaCaja.setCodigoAlterno("C-INICIAL");
			nuevaCaja.setCodigoBarras("CAJA_INICIAL");
			nuevaCaja.setDescripcion("Caja inicial cliente:"+cliente.getNombre());
			nuevaCaja.setEntrepano(nuevoEntrepano);
			Short activo=1;
			nuevaCaja.setEstado(activo);
			nuevaCaja.setQr("CAJA_INICIAL");
			nuevaCaja.setUsuarioCreacion("SYSTEM");
			nuevaCaja.setUsuarioActualizacion("SYSTEM");
			return cajaDAO.crearCaja(nuevaCaja);
		}else {
			return lstCajas.get(0);
		}
	}
	

}

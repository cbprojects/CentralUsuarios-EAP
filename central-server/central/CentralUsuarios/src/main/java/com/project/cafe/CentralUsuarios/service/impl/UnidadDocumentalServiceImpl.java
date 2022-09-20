package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ICajaDao;
import com.project.cafe.CentralUsuarios.dao.IUnidadDocumentalDao;
import com.project.cafe.CentralUsuarios.dto.CajaListDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarArchivoUdDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarListaUdDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUnidadDocumentalDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.dto.UdListDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.model.UnidadDocumentalTB;
import com.project.cafe.CentralUsuarios.service.IUnidadDocumentalService;

@Service
public class UnidadDocumentalServiceImpl implements IUnidadDocumentalService {

	@Autowired
	private IUnidadDocumentalDao unidadDocumentalDAO;
	
	@Autowired
	private ICajaDao cajaDAO;

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
		return unidadDocumentalDAO.consultarUnidadDocumentalFiltros(filtroUnidadDocumental);
	}

	@Override
	public UnidadDocumentalTB buscarUnidadDocumentalPorId(long idUnidadDocumental) {
		return unidadDocumentalDAO.buscarUnidadDocumentalPorId(idUnidadDocumental);
	}

	@Override
	public List<UnidadDocumentalTB> RequestConsultarUnidadDocumentalPorCaja(Long idCaja) {
		return unidadDocumentalDAO.RequestConsultarUnidadDocumentalPorCaja(idCaja);
	}

	@Override
	@Transactional
	public void cambiarCajaUnidadDocumentalMasivo(List<UnidadDocumentalTB> lstUnidadDocumentalCajaUno, Long idCaja) {
		CajaTB nuevaCaja=new CajaTB();
		nuevaCaja=cajaDAO.consultarCajaPorId(idCaja);
		for (UnidadDocumentalTB unidadDocumentalTB : lstUnidadDocumentalCajaUno) {
			unidadDocumentalTB.setCaja(nuevaCaja);
			unidadDocumentalDAO.modificarUnidadDocumental(unidadDocumentalTB);
		}
	}

	@Override
	public List<UnidadDocumentalTB> consultarUnidadDocumentalList(RequestConsultarListaUdDTO request) {
		return unidadDocumentalDAO.consultarUnidadDocumentalList(request);
	}

	@Override
	public List<CajaListDTO> obtenerArchivos(RequestConsultarArchivoUdDTO request) {
		List<UnidadDocumentalTB> listaUD= unidadDocumentalDAO.obtenerArchivos(request);
		List<CajaListDTO> cajaLst =new ArrayList<>();
		if(!listaUD.isEmpty()) {
			for (int i = 0; i < listaUD.size(); i++) {
				if(!cajaLst.isEmpty()) {
					Boolean encontrado=false;
					for (int j = 0; j < cajaLst.size(); j++) {
						if(cajaLst.get(j).idCaja==listaUD.get(i).getCaja().getId()) {
							List<UdListDTO> udList=cajaLst.get(j).lstUdTotales;
							UdListDTO ud= new  UdListDTO();
							ud.setIdUd(listaUD.get(i).getId());
							ud.setCodigoUd(listaUD.get(i).getCodigo());
							String[] strArr = listaUD.get(i).getNombreArchivos().split("#--#");
						    List<String> strList = new ArrayList<String>(Arrays.asList(strArr));
						    ud.setDocumentosUd(strList);
						    udList.add(ud);
						    cajaLst.get(j).setLstUdTotales(udList);
							encontrado=true;
							break;
						}
					}
					if(!encontrado) {
						CajaListDTO caja = new CajaListDTO();
						caja.setIdCaja(listaUD.get(i).getCaja().getId());
						caja.setCodigoCaja(listaUD.get(i).getCaja().getCodigoAlterno());
						List<UdListDTO> udList=new ArrayList<UdListDTO>();
						UdListDTO ud= new  UdListDTO();
						ud.setIdUd(listaUD.get(i).getId());
						ud.setCodigoUd(listaUD.get(i).getCodigo());
						String[] strArr = listaUD.get(i).getNombreArchivos().split("#--#");
					    List<String> strList = new ArrayList<String>(Arrays.asList(strArr));
					    ud.setDocumentosUd(strList);
					    udList.add(ud);
					    caja.setLstUdTotales(udList);
					    cajaLst.add(caja);
					}
				}else {
					CajaListDTO caja = new CajaListDTO();
					caja.setIdCaja(listaUD.get(i).getCaja().getId());
					caja.setCodigoCaja(listaUD.get(i).getCaja().getCodigoAlterno());
					List<UdListDTO> udList=new ArrayList<UdListDTO>();
					UdListDTO ud= new  UdListDTO();
					ud.setIdUd(listaUD.get(i).getId());
					ud.setCodigoUd(listaUD.get(i).getCodigo());
					String[] strArr = listaUD.get(i).getNombreArchivos().split("#--#");
				    List<String> strList = new ArrayList<String>(Arrays.asList(strArr));
				    ud.setDocumentosUd(strList);
				    udList.add(ud);
				    caja.setLstUdTotales(udList);
				    cajaLst.add(caja);
				}
			}
		}
		return cajaLst;
	}
	
	

}

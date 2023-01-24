package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ITipoUDDao;
import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.TipoUDTB;
import com.project.cafe.CentralUsuarios.service.ITipoUDService;

@Service
public class TipoUDServiceImpl implements ITipoUDService {

	@Autowired
	private ITipoUDDao tipoUDDao;
	
	@Transactional
	@Override
	public MasivoDTO  crearTipoUD(MasivoDTO masivo) {
		TipoUDTB tipo = transformarMasivoTipoCrear(masivo);
		tipo=tipoUDDao.crearTipoUD(tipo);
		return transformarTipoMasivo(tipo);
	}

	@Transactional
	@Override
	public MasivoDTO modificarTipoUd(MasivoDTO masivo) {
		TipoUDTB tipo = transformarMasivoTipoEditar(masivo);
		tipo=tipoUDDao.modificarTipoUD(tipo);
		return transformarTipoMasivo(tipo);
	}
	
	@Override
	public List<TipoUDTB> buscarTipoUdPorCodigo(String nombre) {
		return tipoUDDao.buscarTipoUdPorCodigo(nombre);
	}

	@Override
	public List<TipoUDTB> buscarTipoUDActivos() {
		return tipoUDDao.buscarTipoUDActivos();
	}
	
	@Override
	public ResponseConsultarDTO<MasivoDTO> consultarTipoUDFiltros(RequestConsultarMasivoDTO request) {
		ResponseConsultarDTO<TipoUDTB> response =tipoUDDao.consultarTipoUDFiltros(request);
		ResponseConsultarDTO<MasivoDTO> responseMasivo = new ResponseConsultarDTO<>();
		responseMasivo.setRegistrosTotales(response.getRegistrosTotales());
		responseMasivo.setResultado(trasformarListaMasivaTipo(response.getResultado()));
		return responseMasivo;
	}

	private List<MasivoDTO> trasformarListaMasivaTipo(List<TipoUDTB> resultado) {
		List<MasivoDTO> listMasivo = new ArrayList<>();
		if(resultado != null && !resultado.isEmpty()) {
			for (int i = 0; i < resultado.size(); i++) {
				MasivoDTO masivo=transformarTipoMasivo(resultado.get(i));
				listMasivo.add(masivo);
			}
		}
		return listMasivo;
	}
	
	public TipoUDTB transformarMasivoTipoCrear(MasivoDTO masivo) {
		TipoUDTB tipo = new TipoUDTB();
		tipo.setNombre(masivo.getNombre1());
		tipo.setUsuarioCreacion(masivo.getUsuarioCreacion());
		tipo.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		tipo.setFechaCreacion(masivo.getFechaCreacion());
		tipo.setFechaActualizacion(masivo.getFechaActualizacion());
		tipo.setEstado(masivo.getEstado());
		return tipo;
	}
	
	public TipoUDTB transformarMasivoTipoEditar(MasivoDTO masivo) {
		TipoUDTB tipo = new TipoUDTB();
		tipo.setId(masivo.getIdMasivo());
		tipo.setNombre(masivo.getNombre1());
		tipo.setUsuarioCreacion(masivo.getUsuarioCreacion());
		tipo.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		tipo.setFechaCreacion(masivo.getFechaCreacion());
		tipo.setFechaActualizacion(masivo.getFechaActualizacion());
		tipo.setEstado(masivo.getEstado());
		return tipo;
	}
	
	public MasivoDTO transformarTipoMasivo(TipoUDTB tipo) {
		MasivoDTO masivo = new MasivoDTO();
		masivo.setIdMasivo(tipo.getId());
		masivo.setNombre1(tipo.getNombre());
		masivo.setUsuarioCreacion(tipo.getUsuarioCreacion());
		masivo.setUsuarioActualizacion(tipo.getUsuarioActualizacion());
		masivo.setFechaCreacion(tipo.getFechaCreacion());
		masivo.setFechaActualizacion(tipo.getFechaActualizacion());
		masivo.setEstado(tipo.getEstado());
		return masivo;
	}

}

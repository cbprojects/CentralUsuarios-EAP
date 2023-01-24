package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IAreaDao;
import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.service.IAreaService;

@Service
public class AreaServiceImpl implements IAreaService {

	@Autowired
	private IAreaDao areaDAO;

	@Transactional
	@Override
	public MasivoDTO  crearArea(MasivoDTO masivo) {
		AreaTB area = transformarMasivoAreaCrear(masivo);
		area=areaDAO.crearArea(area);
		return transformarAreaMasivo(area);
	}

	@Transactional
	@Override
	public MasivoDTO modificarArea(MasivoDTO masivo) {
		AreaTB area = transformarMasivoAreaEditar(masivo);
		area=areaDAO.modificarArea(area);
		return transformarAreaMasivo(area);
	}
	
	@Override
	public List<AreaTB> buscarAreaPorCodigo(String nombre) {
		return areaDAO.buscarAreaPorCodigo(nombre);
	}
	
	@Override
	public ResponseConsultarDTO<MasivoDTO> consultarAreaFiltros(RequestConsultarMasivoDTO request) {
		ResponseConsultarDTO<AreaTB> response =areaDAO.consultarAreaFiltros(request);
		ResponseConsultarDTO<MasivoDTO> responseMasivo = new ResponseConsultarDTO<>();
		responseMasivo.setRegistrosTotales(response.getRegistrosTotales());
		responseMasivo.setResultado(trasformarListaMasivaArea(response.getResultado()));
		return responseMasivo;
	}

	private List<MasivoDTO> trasformarListaMasivaArea(List<AreaTB> resultado) {
		List<MasivoDTO> listMasivo = new ArrayList<>();
		if(resultado != null && !resultado.isEmpty()) {
			for (int i = 0; i < resultado.size(); i++) {
				MasivoDTO masivo=transformarAreaMasivo(resultado.get(i));
				listMasivo.add(masivo);
			}
		}
		return listMasivo;
	}
	
	public AreaTB transformarMasivoAreaCrear(MasivoDTO masivo) {
		AreaTB area = new AreaTB();
		area.setNombre(masivo.getNombre1());
		area.setNombre10(masivo.getNombre2());
		area.setUsuarioCreacion(masivo.getUsuarioCreacion());
		area.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		area.setFechaCreacion(masivo.getFechaCreacion());
		area.setFechaActualizacion(masivo.getFechaActualizacion());
		area.setEstado(masivo.getEstado());
		return area;
	}

	public AreaTB transformarMasivoAreaEditar(MasivoDTO masivo) {
		AreaTB area = new AreaTB();
		area.setId(masivo.getIdMasivo());
		area.setNombre(masivo.getNombre1());
		area.setNombre10(masivo.getNombre2());
		area.setUsuarioCreacion(masivo.getUsuarioCreacion());
		area.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		area.setFechaCreacion(masivo.getFechaCreacion());
		area.setFechaActualizacion(masivo.getFechaActualizacion());
		area.setEstado(masivo.getEstado());
		return area;
	}
	
	public MasivoDTO transformarAreaMasivo(AreaTB area) {
		MasivoDTO masivo = new MasivoDTO();
		masivo.setIdMasivo(area.getId());
		masivo.setNombre1(area.getNombre());
		masivo.setNombre2(area.getNombre10());
		masivo.setUsuarioCreacion(area.getUsuarioCreacion());
		masivo.setUsuarioActualizacion(area.getUsuarioActualizacion());
		masivo.setFechaCreacion(area.getFechaCreacion());
		masivo.setFechaActualizacion(area.getFechaActualizacion());
		masivo.setEstado(area.getEstado());
		return masivo;
	}
}

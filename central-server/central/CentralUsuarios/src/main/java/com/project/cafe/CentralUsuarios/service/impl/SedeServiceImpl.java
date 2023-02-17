package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.ISedeDao;
import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.SedeTB;
import com.project.cafe.CentralUsuarios.service.ISedeService;

@Service
public class SedeServiceImpl implements ISedeService {

	@Autowired
	private ISedeDao SedeDAO;

	@Transactional
	@Override
	public MasivoDTO  crearSede(MasivoDTO masivo) {
		SedeTB Sede = transformarMasivoSedeCrear(masivo);
		Sede=SedeDAO.crearSede(Sede);
		return transformarSedeMasivo(Sede);
	}

	@Transactional
	@Override
	public MasivoDTO modificarSede(MasivoDTO masivo) {
		SedeTB Sede = transformarMasivoSedeEditar(masivo);
		Sede=SedeDAO.modificarSede(Sede);
		return transformarSedeMasivo(Sede);
	}
	
	@Override
	public List<SedeTB> buscarSedePorCodigo(String nombre) {
		return SedeDAO.buscarSedePorCodigo(nombre);
	}
	
	@Override
	public ResponseConsultarDTO<MasivoDTO> consultarSedeFiltros(RequestConsultarMasivoDTO request) {
		ResponseConsultarDTO<SedeTB> response =SedeDAO.consultarSedeFiltros(request);
		ResponseConsultarDTO<MasivoDTO> responseMasivo = new ResponseConsultarDTO<>();
		responseMasivo.setRegistrosTotales(response.getRegistrosTotales());
		responseMasivo.setResultado(trasformarListaMasivaSede(response.getResultado()));
		return responseMasivo;
	}

	private List<MasivoDTO> trasformarListaMasivaSede(List<SedeTB> resultado) {
		List<MasivoDTO> listMasivo = new ArrayList<>();
		if(resultado != null && !resultado.isEmpty()) {
			for (int i = 0; i < resultado.size(); i++) {
				MasivoDTO masivo=transformarSedeMasivo(resultado.get(i));
				listMasivo.add(masivo);
			}
		}
		return listMasivo;
	}
	
	public SedeTB transformarMasivoSedeCrear(MasivoDTO masivo) {
		SedeTB Sede = new SedeTB();
		Sede.setNombre(masivo.getNombre1());
		Sede.setNombre10(masivo.getNombre2());
		Sede.setAddress(masivo.getNombre3());
		Sede.setUsuarioCreacion(masivo.getUsuarioCreacion());
		Sede.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		Sede.setFechaCreacion(masivo.getFechaCreacion());
		Sede.setFechaActualizacion(masivo.getFechaActualizacion());
		Sede.setEstado(masivo.getEstado());
		return Sede;
	}

	public SedeTB transformarMasivoSedeEditar(MasivoDTO masivo) {
		SedeTB Sede = new SedeTB();
		Sede.setId(masivo.getIdMasivo());
		Sede.setNombre(masivo.getNombre1());
		Sede.setNombre10(masivo.getNombre2());
		Sede.setAddress(masivo.getNombre3());
		Sede.setUsuarioCreacion(masivo.getUsuarioCreacion());
		Sede.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		Sede.setFechaCreacion(masivo.getFechaCreacion());
		Sede.setFechaActualizacion(masivo.getFechaActualizacion());
		Sede.setEstado(masivo.getEstado());
		return Sede;
	}
	
	public MasivoDTO transformarSedeMasivo(SedeTB Sede) {
		MasivoDTO masivo = new MasivoDTO();
		masivo.setIdMasivo(Sede.getId());
		masivo.setNombre1(Sede.getNombre());
		masivo.setNombre2(Sede.getNombre10());
		masivo.setNombre3(Sede.getAddress());
		masivo.setUsuarioCreacion(Sede.getUsuarioCreacion());
		masivo.setUsuarioActualizacion(Sede.getUsuarioActualizacion());
		masivo.setFechaCreacion(Sede.getFechaCreacion());
		masivo.setFechaActualizacion(Sede.getFechaActualizacion());
		masivo.setEstado(Sede.getEstado());
		return masivo;
	}
}

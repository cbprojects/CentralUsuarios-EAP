package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IContenedorDao;
import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ContenedorUDTB;
import com.project.cafe.CentralUsuarios.service.IContenedorService;

@Service
public class ContenedorServiceImpl implements IContenedorService {

	@Autowired
	private IContenedorDao contenedorDao;
	
	@Transactional
	@Override
	public MasivoDTO  crearContenedor(MasivoDTO masivo) {
		ContenedorUDTB contenedor = transformarMasivoContenedorCrear(masivo);
		contenedor=contenedorDao.crearContenedor(contenedor);
		return transformarContenedorMasivo(contenedor);
	}

	@Transactional
	@Override
	public MasivoDTO modificarContenedor(MasivoDTO masivo) {
		ContenedorUDTB contenedor = transformarMasivoContenedorEditar(masivo);
		contenedor=contenedorDao.modificarContenedor(contenedor);
		return transformarContenedorMasivo(contenedor);
	}
	
	@Override
	public List<ContenedorUDTB> buscarContenedorPorCodigo(String nombre) {
		return contenedorDao.buscarContenedorPorCodigo(nombre);
	}

	@Override
	public List<ContenedorUDTB> buscarContenedoresActivos() {
		return contenedorDao.buscarContenedoresActivos();
	}
	
	@Override
	public ResponseConsultarDTO<MasivoDTO> consultarContenedorFiltros(RequestConsultarMasivoDTO request) {
		ResponseConsultarDTO<ContenedorUDTB> response =contenedorDao.consultarContenedorFiltros(request);
		ResponseConsultarDTO<MasivoDTO> responseMasivo = new ResponseConsultarDTO<>();
		responseMasivo.setRegistrosTotales(response.getRegistrosTotales());
		responseMasivo.setResultado(trasformarListaMasivaContenedor(response.getResultado()));
		return responseMasivo;
	}

	private List<MasivoDTO> trasformarListaMasivaContenedor(List<ContenedorUDTB> resultado) {
		List<MasivoDTO> listMasivo = new ArrayList<>();
		if(resultado != null && !resultado.isEmpty()) {
			for (int i = 0; i < resultado.size(); i++) {
				MasivoDTO masivo=transformarContenedorMasivo(resultado.get(i));
				listMasivo.add(masivo);
			}
		}
		return listMasivo;
	}
	
	public ContenedorUDTB transformarMasivoContenedorCrear(MasivoDTO masivo) {
		ContenedorUDTB contenedor = new ContenedorUDTB();
		contenedor.setNombre(masivo.getNombre1());
		contenedor.setUsuarioCreacion(masivo.getUsuarioCreacion());
		contenedor.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		contenedor.setFechaCreacion(masivo.getFechaCreacion());
		contenedor.setFechaActualizacion(masivo.getFechaActualizacion());
		contenedor.setEstado(masivo.getEstado());
		return contenedor;
	}
	
	public ContenedorUDTB transformarMasivoContenedorEditar(MasivoDTO masivo) {
		ContenedorUDTB contenedor = new ContenedorUDTB();
		contenedor.setId(masivo.getIdMasivo());
		contenedor.setNombre(masivo.getNombre1());
		contenedor.setUsuarioCreacion(masivo.getUsuarioCreacion());
		contenedor.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		contenedor.setFechaCreacion(masivo.getFechaCreacion());
		contenedor.setFechaActualizacion(masivo.getFechaActualizacion());
		contenedor.setEstado(masivo.getEstado());
		return contenedor;
	}
	
	public MasivoDTO transformarContenedorMasivo(ContenedorUDTB contenedor) {
		MasivoDTO masivo = new MasivoDTO();
		masivo.setIdMasivo(contenedor.getId());
		masivo.setNombre1(contenedor.getNombre());
		masivo.setUsuarioCreacion(contenedor.getUsuarioCreacion());
		masivo.setUsuarioActualizacion(contenedor.getUsuarioActualizacion());
		masivo.setFechaCreacion(contenedor.getFechaCreacion());
		masivo.setFechaActualizacion(contenedor.getFechaActualizacion());
		masivo.setEstado(contenedor.getEstado());
		return masivo;
	}

}

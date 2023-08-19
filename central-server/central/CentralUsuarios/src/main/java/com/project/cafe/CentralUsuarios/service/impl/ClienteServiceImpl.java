package com.project.cafe.CentralUsuarios.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IClienteDao;
import com.project.cafe.CentralUsuarios.dto.MasivoDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Transactional
	@Override
	public MasivoDTO  crearCliente(MasivoDTO masivo) {
		ClienteTB cliente = transformarMasivoClienteCrear(masivo);
		cliente=clienteDao.crearCliente(cliente);
		return transformarClienteMasivo(cliente);
	}

	@Transactional
	@Override
	public MasivoDTO modificarCliente(MasivoDTO masivo) {
		ClienteTB cliente = transformarMasivoClienteEditar(masivo);
		Integer numeroF=clienteDao.buscarClientePorIDNumeroFactura(cliente.getId());
		cliente.setNumeroFactura(numeroF);
		cliente=clienteDao.modificarCliente(cliente);
		return transformarClienteMasivo(cliente);
	}
	
	@Override
	public List<ClienteTB> buscarClientePorCodigo(String nombre) {
		return clienteDao.buscarClientePorCodigo(nombre);
	}

	@Override
	public List<ClienteTB> buscarClientesActivos(){
		return clienteDao.buscarClientesActivos();
	}
	
	@Override
	public ResponseConsultarDTO<MasivoDTO> consultarClienteFiltros(RequestConsultarMasivoDTO request) {
		ResponseConsultarDTO<ClienteTB> response =clienteDao.consultarClienteFiltros(request);
		ResponseConsultarDTO<MasivoDTO> responseMasivo = new ResponseConsultarDTO<>();
		responseMasivo.setRegistrosTotales(response.getRegistrosTotales());
		responseMasivo.setResultado(trasformarListaMasivaCliente(response.getResultado()));
		return responseMasivo;
	}

	private List<MasivoDTO> trasformarListaMasivaCliente(List<ClienteTB> resultado) {
		List<MasivoDTO> listMasivo = new ArrayList<>();
		if(resultado != null && !resultado.isEmpty()) {
			for (int i = 0; i < resultado.size(); i++) {
				MasivoDTO masivo=transformarClienteMasivo(resultado.get(i));
				listMasivo.add(masivo);
			}
		}
		return listMasivo;
	}
	
	public ClienteTB transformarMasivoClienteCrear(MasivoDTO masivo) {
		ClienteTB cliente = new ClienteTB();
		cliente.setNombre(masivo.getNombre1());
		cliente.setTax(masivo.getNombre2());
		cliente.setNumeroFactura(0);
		cliente.setUsuarioCreacion(masivo.getUsuarioCreacion());
		cliente.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		cliente.setFechaCreacion(masivo.getFechaCreacion());
		cliente.setFechaActualizacion(masivo.getFechaActualizacion());
		cliente.setEstado(masivo.getEstado());
		return cliente;
	}
	
	public ClienteTB transformarMasivoClienteEditar(MasivoDTO masivo) {
		ClienteTB cliente = new ClienteTB();
		cliente.setId(masivo.getIdMasivo());
		cliente.setNombre(masivo.getNombre1());
		cliente.setTax(masivo.getNombre2());
		cliente.setUsuarioCreacion(masivo.getUsuarioCreacion());
		cliente.setUsuarioActualizacion(masivo.getUsuarioActualizacion());
		cliente.setFechaCreacion(masivo.getFechaCreacion());
		cliente.setFechaActualizacion(masivo.getFechaActualizacion());
		cliente.setEstado(masivo.getEstado());
		return cliente;
	}
	
	public MasivoDTO transformarClienteMasivo(ClienteTB cliente) {
		MasivoDTO masivo = new MasivoDTO();
		masivo.setIdMasivo(cliente.getId());
		masivo.setNombre1(cliente.getNombre());
		masivo.setNombre2(cliente.getTax());
		masivo.setUsuarioCreacion(cliente.getUsuarioCreacion());
		masivo.setUsuarioActualizacion(cliente.getUsuarioActualizacion());
		masivo.setFechaCreacion(cliente.getFechaCreacion());
		masivo.setFechaActualizacion(cliente.getFechaActualizacion());
		masivo.setEstado(cliente.getEstado());
		return masivo;
	}

}

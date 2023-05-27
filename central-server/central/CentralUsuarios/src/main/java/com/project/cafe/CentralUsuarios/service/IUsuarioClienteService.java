package com.project.cafe.CentralUsuarios.service;

import java.util.List;

import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuarioClienteDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.model.UsuarioClienteTB;

public interface IUsuarioClienteService {

	
	List<ClienteTB> buscarClientesActivosPorUsuario(long idU);
	
	UsuarioClienteTB modificarUsuarioCliente(UsuarioClienteTB usuarioCliente);
	
	UsuarioClienteTB crearUsuarioCliente(UsuarioClienteTB newUsuarioSede);
	
	List<UsuarioClienteTB> buscarUsuarioClientePorId(long idU, long idC);

	ResponseConsultarDTO<UsuarioClienteTB> consultarUsuarioClienteFiltros(RequestConsultarUsuarioClienteDTO request);

}

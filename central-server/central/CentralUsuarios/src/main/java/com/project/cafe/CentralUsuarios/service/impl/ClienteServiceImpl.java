package com.project.cafe.CentralUsuarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cafe.CentralUsuarios.dao.IClienteDao;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Override
	public List<ClienteTB> buscarClientesActivos(){
		return clienteDao.buscarClientesActivos();
	}

}

package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IUsuarioClienteDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarUsuarioClienteDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.model.UsuarioClienteTB;

@Repository
public class UsuarioClienteDaoImpl extends AbstractDao<UsuarioClienteTB> implements IUsuarioClienteDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;
	
	@Override
	@Transactional
	public UsuarioClienteTB crearUsuarioCliente(UsuarioClienteTB newUsuarioCliente) {
		super.create(newUsuarioCliente);
		return newUsuarioCliente;
	}


	@Override
	@Transactional
	public UsuarioClienteTB modificarUsuarioCliente(UsuarioClienteTB newUsuarioCliente) {
		super.update(newUsuarioCliente);
		return newUsuarioCliente;
	}
	
	@Override
	public List<UsuarioClienteTB> buscarUsuarioClientePorId(long idU, long idC) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM UsuarioClienteTB r WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND r.usuario.id = :IDU ");
		pamameters.put("IDU", idU);
		
		JPQL.append(" AND r.cliente.id = :IDC ");
		pamameters.put("IDC", idC);
		// Q. Order By
		JPQL.append(" ORDER BY r.id");
		// END QUERY

		TypedQuery<UsuarioClienteTB> query = em.createQuery(JPQL.toString(), UsuarioClienteTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}
	
	@Override
	public ResponseConsultarDTO<UsuarioClienteTB> consultarUsuarioClienteFiltros(RequestConsultarUsuarioClienteDTO request) {

		ResponseConsultarDTO<UsuarioClienteTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM UsuarioClienteTB r WHERE 1 = 1 ");
		// WHERE
		if (request.getUsuarioCliente().getUsuario()!=null && request.getUsuarioCliente().getUsuario().getId()!=0l) {
			JPQL.append(" AND r.usuario.id = :IDU ");
			pamametros.put("IDU", request.getUsuarioCliente().getUsuario().getId());
		}

		if (request.getUsuarioCliente().getCliente()!=null && request.getUsuarioCliente().getCliente().getId()!=0l) {
			JPQL.append(" AND r.cliente.id = :IDA ");
			pamametros.put("IDA", request.getUsuarioCliente().getCliente().getId());
		}

		String COUNT = "SELECT COUNT(r) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY r.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<UsuarioClienteTB> query = em.createQuery(JPQL.toString(), UsuarioClienteTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<UsuarioClienteTB> listaSociedadArea= query.getResultList();

		response.setResultado(listaSociedadArea);

		return response;
	}

	@Override
	public List<ClienteTB> buscarClientesActivosPorUsuario(long idU) {
		List<ClienteTB> clientes = new ArrayList<>();
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT us FROM UsuarioClienteTB us WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND us.usuario.id = :IDU ");
		pamameters.put("IDU", idU);
		
		// Q. Order By
		JPQL.append(" ORDER BY us.id");
		// END QUERY

		TypedQuery<UsuarioClienteTB> query = em.createQuery(JPQL.toString(), UsuarioClienteTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		List<UsuarioClienteTB> usuarioClientes = query.getResultList();
		
		if(!usuarioClientes.isEmpty()) {
			for (UsuarioClienteTB usuarioClienteTB : usuarioClientes) {
				clientes.add(usuarioClienteTB.getCliente());
			}
		}

		return clientes;
	}

}

package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IClienteDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ClienteTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class ClienteDaoImpl extends AbstractDao<ClienteTB> implements IClienteDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;
	
	@Override
	public ClienteTB crearCliente(ClienteTB cliente) {
		super.create(cliente);
		return cliente;
	}

	@Override
	public ClienteTB modificarCliente(ClienteTB cliente) {
		super.update(cliente);
		return cliente;
	}

	@Override
	public List<ClienteTB> buscarClientePorCodigo(String nombre) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM ClienteTB r WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(nombre)) {
			JPQL.append("AND UPPER(r.nombre) = :NOMBRE ");
			pamameters.put("NOMBRE", nombre.toUpperCase());
		}
		// Q. Order By
		JPQL.append(" ORDER BY r.id");
		// END QUERY

		TypedQuery<ClienteTB> query = em.createQuery(JPQL.toString(), ClienteTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}
	
	@Override
	public ResponseConsultarDTO<ClienteTB> consultarClienteFiltros(RequestConsultarMasivoDTO request){

		ResponseConsultarDTO<ClienteTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM ClienteTB r WHERE 1 = 1 ");
		// WHERE
		if (StringUtils.isNotBlank(request.getMasivo().getNombre1())) {
			JPQL.append(" AND UPPER(r.nombre) LIKE UPPER(:NOMBRE) ");
			pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD + request.getMasivo().getNombre1()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		if (StringUtils.isNotBlank(request.getMasivo().getNombre2())) {
			JPQL.append(" AND UPPER(r.tax) LIKE UPPER(:TAX) ");
			pamametros.put("TAX", ConstantesValidaciones.COMODIN_BD + request.getMasivo().getNombre2()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		String COUNT = "SELECT COUNT(r) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY r.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<ClienteTB> query = em.createQuery(JPQL.toString(), ClienteTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<ClienteTB> listaCliente = query.getResultList();

		response.setResultado(listaCliente);

		return response;
	}

	@Override
	public List<ClienteTB> buscarClientesActivos() {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT c FROM ClienteTB c WHERE 1 = 1 ");
		// WHERE
			JPQL.append("AND c.estado = 1 ");
			
		// Q. Order By
		JPQL.append(" ORDER BY c.id");
		// END QUERY

		TypedQuery<ClienteTB> query = em.createQuery(JPQL.toString(), ClienteTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public Integer buscarClientePorIDNumeroFactura(long id) {
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT c FROM ClienteTB c WHERE 1 = 1 ");
		// WHERE
			JPQL.append("AND c.estado = 1 ");
			JPQL.append("AND c.id = :ID ");
			pamameters.put("ID", id);
		// Q. Order By
		JPQL.append(" ORDER BY c.id");
		// END QUERY

		TypedQuery<ClienteTB> query = em.createQuery(JPQL.toString(), ClienteTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList().get(0).getNumeroFactura();
	}

}

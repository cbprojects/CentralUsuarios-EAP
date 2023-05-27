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
import com.project.cafe.CentralUsuarios.dao.IActaDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarActaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.ActaTB;
import com.project.cafe.CentralUsuarios.model.CajaTB;

@Repository
public class ActaDaoImpl extends AbstractDao<ActaTB> implements IActaDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public ActaTB crearActa(ActaTB acta) {
		super.create(acta);
		return acta;
	}

	@Override
	public ActaTB modificarActa(ActaTB acta) {
		super.update(acta);
		return acta;
	}

//	@Override
	public ResponseConsultarDTO<ActaTB> consultarActaFiltros(RequestConsultarActaDTO request) {

		ResponseConsultarDTO<ActaTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT a FROM ActaTB a WHERE 1 = 1 ");
		// WHERE
		if (Boolean.FALSE.equals(request.getEsAdmin())) {
			JPQL.append(" AND a.usuario.id = :IDUSER ");
			pamametros.put("IDUSER", request.getIdUsuario());
		}

		if (request.getTipoAprobado() != 0) {
			JPQL.append(" AND a.aprobada = :APROBADO ");
			pamametros.put("APROBADO", request.getTipoAprobado() == 1 ? true : false);
		}

		String COUNT = "SELECT COUNT(a) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY a.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<ActaTB> query = em.createQuery(JPQL.toString(), ActaTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<ActaTB> listaCajas = query.getResultList();

		response.setResultado(listaCajas);

		return response;
	}

	@Override
	public List<ActaTB> buscarActaPorId(Long idUD) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT a FROM ActaTB a WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND a.id = :ID ");
		pamameters.put("ID", idUD);

		// Q. Order By
		JPQL.append(" ORDER BY a.id");
		// END QUERY

		TypedQuery<ActaTB> query = em.createQuery(JPQL.toString(), ActaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public boolean validarActasNoAprobadasCliente(long id) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT a FROM ActaTB a WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND a.cliente.id = :IDCLIENTE ");
		pamameters.put("IDCLIENTE", id);

		JPQL.append(" AND a.aprobada = :APROBADO ");
		pamameters.put("APROBADO", false);

		// Q. Order By
		JPQL.append(" ORDER BY a.id");
		// END QUERY

		TypedQuery<ActaTB> query = em.createQuery(JPQL.toString(), ActaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList().isEmpty();

	}

}

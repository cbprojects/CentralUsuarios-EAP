package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IBloqueDao;
import com.project.cafe.CentralUsuarios.model.BloqueTB;

@Repository
public class BloqueDaoImpl extends AbstractDao<BloqueTB> implements IBloqueDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public List<BloqueTB> buscarBloquesActivosPorBodega(Long idBodega) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT b FROM BloqueTB b WHERE 1 = 1 ");
		// WHERE
		JPQL.append("AND b.bodega.id = :IDBODEGA ");
		pamameters.put("IDBODEGA", idBodega);

		JPQL.append("AND b.estado = 1");
		// Q. Order By
		JPQL.append(" ORDER BY b.id");
		// END QUERY

		TypedQuery<BloqueTB> query = em.createQuery(JPQL.toString(), BloqueTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}
}

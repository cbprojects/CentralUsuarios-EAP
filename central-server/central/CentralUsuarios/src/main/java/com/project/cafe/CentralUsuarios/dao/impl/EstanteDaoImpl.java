package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IEstanteDao;
import com.project.cafe.CentralUsuarios.model.EstanteTB;

@Repository
public class EstanteDaoImpl extends AbstractDao<EstanteTB> implements IEstanteDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public List<EstanteTB> buscarEstantesActivosPorCuerpo(Long idCuerpo) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT e FROM EstanteTB e WHERE 1 = 1 ");
		// WHERE
			JPQL.append("AND e.cuerpo.id = :IDCUERPO ");
			pamameters.put("IDCUERPO", idCuerpo);
			
			JPQL.append("AND e.estado = 1");
		// Q. Order By
		JPQL.append(" ORDER BY e.id");
		// END QUERY

		TypedQuery<EstanteTB> query = em.createQuery(JPQL.toString(), EstanteTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}

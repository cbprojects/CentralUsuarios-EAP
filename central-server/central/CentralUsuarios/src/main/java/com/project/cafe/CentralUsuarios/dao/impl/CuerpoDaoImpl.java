package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IBloqueDao;
import com.project.cafe.CentralUsuarios.dao.IBodegaDao;
import com.project.cafe.CentralUsuarios.dao.ICuerpoDao;
import com.project.cafe.CentralUsuarios.model.BloqueTB;
import com.project.cafe.CentralUsuarios.model.BodegaTB;
import com.project.cafe.CentralUsuarios.model.CuerpoTB;

@Repository
public class CuerpoDaoImpl extends AbstractDao<CuerpoTB> implements ICuerpoDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public List<CuerpoTB> buscarCuerposActivosPorBloque(Long idBloque) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT c FROM CuerpoTB c WHERE 1 = 1 ");
		// WHERE
			JPQL.append("AND c.bloque.id = :IDBLOQUE ");
			pamameters.put("IDBLOQUE", idBloque);
			
			JPQL.append("AND c.estado = 1");
		// Q. Order By
		JPQL.append(" ORDER BY c.id");
		// END QUERY

		TypedQuery<CuerpoTB> query = em.createQuery(JPQL.toString(), CuerpoTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}

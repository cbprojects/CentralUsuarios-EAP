package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IContenedorDao;
import com.project.cafe.CentralUsuarios.model.ContenedorUDTB;

@Repository
public class ContenedorDaoImpl extends AbstractDao<ContenedorUDTB> implements IContenedorDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public List<ContenedorUDTB> buscarContenedoresActivos() {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT c FROM ContenedorUDTB c WHERE 1 = 1 ");
		// WHERE
			JPQL.append("AND c.estado = 1 ");
			
		// Q. Order By
		JPQL.append(" ORDER BY c.id");
		// END QUERY

		TypedQuery<ContenedorUDTB> query = em.createQuery(JPQL.toString(), ContenedorUDTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}

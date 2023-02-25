package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IServidorDao;
import com.project.cafe.CentralUsuarios.model.ServidorTB;

@Repository
public class ServidorDaoImpl extends AbstractDao<ServidorTB> implements IServidorDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public List<ServidorTB> consultarServidorActivo() {

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT s FROM ServidorTB s WHERE s.estado = 1 ");

		// Q. Order By
		JPQL.append(" ORDER BY s.id");
		// END QUERY

		TypedQuery<ServidorTB> query = em.createQuery(JPQL.toString(), ServidorTB.class);
		
		return query.getResultList();
	}

}

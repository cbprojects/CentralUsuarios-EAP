package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.ISociedadDao;
import com.project.cafe.CentralUsuarios.model.SociedadTB;

@Repository
public class SociedadDaoImpl extends AbstractDao<SociedadTB> implements ISociedadDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public List<SociedadTB> buscarSociedadActiva() {

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT s FROM SociedadTB s WHERE s.estado = 1 ");

		// Q. Order By
		JPQL.append(" ORDER BY s.id");
		// END QUERY

		TypedQuery<SociedadTB> query = em.createQuery(JPQL.toString(), SociedadTB.class);
		
		return query.getResultList();
	}

}

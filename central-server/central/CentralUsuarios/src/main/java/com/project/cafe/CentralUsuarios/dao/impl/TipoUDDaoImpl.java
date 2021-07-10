package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.ITipoUDDao;
import com.project.cafe.CentralUsuarios.model.TipoUDTB;

@Repository
public class TipoUDDaoImpl extends AbstractDao<TipoUDTB> implements ITipoUDDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public List<TipoUDTB> buscarTipoUDActivos() {

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT tud FROM TipoUDTB tud WHERE 1 = 1 ");
		// WHERE
			JPQL.append("AND tud.estado = 1 ");
			
		// Q. Order By
		JPQL.append(" ORDER BY tud.id");
		// END QUERY

		TypedQuery<TipoUDTB> query = em.createQuery(JPQL.toString(), TipoUDTB.class);	

		return query.getResultList();
	}

}

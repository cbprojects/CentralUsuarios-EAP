package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IPrestamoHistoricoDao;
import com.project.cafe.CentralUsuarios.model.PrestamoHistoricoTB;

@Repository
public class PrestamoHistoricoDaoImpl extends AbstractDao<PrestamoHistoricoTB> implements IPrestamoHistoricoDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public PrestamoHistoricoTB crearPrestamoHistorico(PrestamoHistoricoTB prestamoHis) {
		super.create(prestamoHis);
		return prestamoHis;
	}

	@Override
	public List<PrestamoHistoricoTB> buscarPrestamoHistorico(Long idUd) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM PrestamoHistoricoTB r WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND r.idUd =:IDUD ");
		pamameters.put("IDUD", idUd);
		
		// Q. Order By
		JPQL.append(" ORDER BY r.id desc");
		// END QUERY

		TypedQuery<PrestamoHistoricoTB> query = em.createQuery(JPQL.toString(), PrestamoHistoricoTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}

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
import com.project.cafe.CentralUsuarios.dao.IEntrepanoDao;
import com.project.cafe.CentralUsuarios.dao.IEstanteDao;
import com.project.cafe.CentralUsuarios.model.BloqueTB;
import com.project.cafe.CentralUsuarios.model.BodegaTB;
import com.project.cafe.CentralUsuarios.model.CuerpoTB;
import com.project.cafe.CentralUsuarios.model.EntrepanoTB;
import com.project.cafe.CentralUsuarios.model.EstanteTB;

@Repository
public class EntrepanoDaoImpl extends AbstractDao<EntrepanoTB> implements IEntrepanoDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public List<EntrepanoTB> buscarEntrepanosActivosPorEstante(Long idEstante) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT e FROM EntrepanoTB e WHERE 1 = 1 ");
		// WHERE
			JPQL.append("AND e.estante.id = :IDESTANTE ");
			pamameters.put("IDESTANTE", idEstante);
			
			JPQL.append("AND e.estado = 1");
		// Q. Order By
		JPQL.append(" ORDER BY e.id");
		// END QUERY

		TypedQuery<EntrepanoTB> query = em.createQuery(JPQL.toString(), EntrepanoTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}
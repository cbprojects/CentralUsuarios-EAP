package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.ISociedadAreaDao;
import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.model.SociedadAreaTB;

@Repository
public class SociedadAreaDaoImpl extends AbstractDao<SociedadAreaTB> implements ISociedadAreaDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public List<AreaTB> buscarAreasActivasPorSociedad(Long idSociedad) {

		List<AreaTB> listaAreas = new ArrayList<>();
		List<SociedadAreaTB> listaSociedadArea;
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT sa FROM SociedadAreaTB sa WHERE 1 = 1 ");
		// WHERE
		JPQL.append("AND sa.area.estado = 1 ");
		
		JPQL.append("AND sa.sociedad.id = :IDSOCIEDAD ");
		pamameters.put("IDSOCIEDAD", idSociedad);

		// Q. Order By
		JPQL.append(" ORDER BY sa.id");
		// END QUERY

		TypedQuery<SociedadAreaTB> query = em.createQuery(JPQL.toString(), SociedadAreaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		listaSociedadArea = query.getResultList();

		if (!listaSociedadArea.isEmpty()) {
			for (Iterator iterator = listaSociedadArea.iterator(); iterator.hasNext();) {
				SociedadAreaTB sociedadArea = (SociedadAreaTB) iterator.next();
				listaAreas.add(sociedadArea.getArea());
			}
		}

		return listaAreas;
	}

}

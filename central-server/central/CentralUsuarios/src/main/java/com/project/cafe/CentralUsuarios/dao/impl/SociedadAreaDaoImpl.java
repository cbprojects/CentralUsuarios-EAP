package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.ISociedadAreaDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarSociedadAreaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.model.SociedadAreaTB;

@Repository
public class SociedadAreaDaoImpl extends AbstractDao<SociedadAreaTB> implements ISociedadAreaDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;
	
	@Override
	@Transactional
	public SociedadAreaTB crearSociedadArea(SociedadAreaTB newSociedadArea) {
		super.create(newSociedadArea);
		return newSociedadArea;
	}

	@Override
	@Transactional
	public SociedadAreaTB modificarSociedadArea(SociedadAreaTB newSociedadArea) {
		super.update(newSociedadArea);
		return newSociedadArea;
	}
	
	@Override
	public List<SociedadAreaTB> buscarSociedadAreaPorId(long idS, long idA) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM SociedadAreaTB r WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND r.sociedad.id = :IDS ");
		pamameters.put("IDS", idS);
		
		JPQL.append(" AND r.area.id = :IDA ");
		pamameters.put("IDA", idA);
		// Q. Order By
		JPQL.append(" ORDER BY r.id");
		// END QUERY

		TypedQuery<SociedadAreaTB> query = em.createQuery(JPQL.toString(), SociedadAreaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}
	
	@Override
	public ResponseConsultarDTO<SociedadAreaTB> consultarRolesFiltros(RequestConsultarSociedadAreaDTO request) {

		ResponseConsultarDTO<SociedadAreaTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM SociedadAreaTB r WHERE 1 = 1 ");
		// WHERE
		if (request.getSociedadArea().getSociedad()!=null && request.getSociedadArea().getSociedad().getId()!=0l) {
			JPQL.append(" AND r.sociedad.id = :IDS ");
			pamametros.put("IDS", request.getSociedadArea().getSociedad().getId());
		}

		if (request.getSociedadArea().getArea()!=null && request.getSociedadArea().getArea().getId()!=0l) {
			JPQL.append(" AND r.area.id = :IDA ");
			pamametros.put("IDA", request.getSociedadArea().getArea().getId());
		}

		String COUNT = "SELECT COUNT(r) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY r.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<SociedadAreaTB> query = em.createQuery(JPQL.toString(), SociedadAreaTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<SociedadAreaTB> listaSociedadArea= query.getResultList();

		response.setResultado(listaSociedadArea);

		return response;
	}

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

	@Override
	public List<SociedadAreaTB> buscarSociedadAreaPorSociedadArea(long idSociedad, long idArea) {
		List<SociedadAreaTB> listaSociedadArea = new ArrayList<>();;
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT sa FROM SociedadAreaTB sa WHERE 1 = 1 ");
		// WHERE
		JPQL.append(" AND sa.area.id = :IDAREA ");
		pamameters.put("IDAREA", idArea);
		
		JPQL.append(" AND sa.sociedad.id = :IDSOCIEDAD ");
		pamameters.put("IDSOCIEDAD", idSociedad);

		// Q. Order By
		JPQL.append(" ORDER BY sa.id");
		// END QUERY

		TypedQuery<SociedadAreaTB> query = em.createQuery(JPQL.toString(), SociedadAreaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		listaSociedadArea = query.getResultList();

		return listaSociedadArea;
	}

}

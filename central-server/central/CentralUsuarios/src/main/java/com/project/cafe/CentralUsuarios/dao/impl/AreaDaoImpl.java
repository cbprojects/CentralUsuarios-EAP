package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IAreaDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarMasivoDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.AreaTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class AreaDaoImpl extends AbstractDao<AreaTB> implements IAreaDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public AreaTB crearArea(AreaTB area) {
		super.create(area);
		return area;
	}

	@Override
	public AreaTB modificarArea(AreaTB area) {
		super.update(area);
		return area;
	}

	@Override
	public List<AreaTB> buscarAreaPorCodigo(String nombre) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM AreaTB r WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(nombre)) {
			JPQL.append(" AND UPPER(r.nombre) =:NOMBRE ");
			pamameters.put("NOMBRE", nombre.toUpperCase());
		}
		// Q. Order By
		JPQL.append(" ORDER BY r.id");
		// END QUERY

		TypedQuery<AreaTB> query = em.createQuery(JPQL.toString(), AreaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}
	
	@Override
	public List<AreaTB> buscarAreaPorId(Long id) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM AreaTB r WHERE 1 = 1 ");
		// WHERE
		if (id != null) {
			JPQL.append(" AND r.id =:ID ");
			pamameters.put("ID", id);
		}
		// Q. Order By
		JPQL.append(" ORDER BY r.id");
		// END QUERY

		TypedQuery<AreaTB> query = em.createQuery(JPQL.toString(), AreaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public ResponseConsultarDTO<AreaTB> consultarAreaFiltros(RequestConsultarMasivoDTO request) {

		ResponseConsultarDTO<AreaTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT r FROM AreaTB r WHERE 1 = 1 ");
		// WHERE
		if (StringUtils.isNotBlank(request.getMasivo().getNombre1())) {
			JPQL.append(" AND UPPER(r.nombre) LIKE UPPER(:NOMBRE) ");
			pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD + request.getMasivo().getNombre1()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		if (StringUtils.isNotBlank(request.getMasivo().getNombre2())) {
			JPQL.append(" AND UPPER(r.nombre10) LIKE UPPER(:NOMBRE10) ");
			pamametros.put("NOMBRE10", ConstantesValidaciones.COMODIN_BD + request.getMasivo().getNombre2()
					+ ConstantesValidaciones.COMODIN_BD);
		}

		String COUNT = "SELECT COUNT(r) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY r.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<AreaTB> query = em.createQuery(JPQL.toString(), AreaTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<AreaTB> listaArea = query.getResultList();

		response.setResultado(listaArea);

		return response;
	}


}

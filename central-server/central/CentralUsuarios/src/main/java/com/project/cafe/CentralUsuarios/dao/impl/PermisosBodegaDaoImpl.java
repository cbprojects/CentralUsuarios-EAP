package com.project.cafe.CentralUsuarios.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.project.cafe.CentralUsuarios.dao.AbstractDao;
import com.project.cafe.CentralUsuarios.dao.IPermisosBodegaDao;
import com.project.cafe.CentralUsuarios.dto.RequestBuscarPermisosBodegaUsuarioDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarPermisosBodegaDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.PermisosBodegaTB;

@Repository
public class PermisosBodegaDaoImpl extends AbstractDao<PermisosBodegaTB> implements IPermisosBodegaDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public PermisosBodegaTB crearPermisosBodega(PermisosBodegaTB permisosBodega) {
		super.create(permisosBodega);
		return permisosBodega;
	}

	@Override
	public PermisosBodegaTB modificarPermisosBodega(PermisosBodegaTB permisosBodega) {
		super.update(permisosBodega);
		return permisosBodega;
	}

	@Override
	public List<PermisosBodegaTB> buscarPermisosPorBodegaUsuario(long idBodega, long idUsuario) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT p FROM PermisosBodegaTB p WHERE 1 = 1 ");
		// WHERE
		if (idBodega != 0) {
			JPQL.append(" AND p.bodega.id = :BODEGA ");
			pamameters.put("BODEGA", idBodega);
		}
		if (idUsuario != 0) {
			JPQL.append(" AND p.usuario.id = :USUARIO ");
			pamameters.put("USUARIO", idUsuario);
		}
		// Q. Order By
		JPQL.append(" ORDER BY p.id");
		// END QUERY

		TypedQuery<PermisosBodegaTB> query = em.createQuery(JPQL.toString(), PermisosBodegaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public ResponseConsultarDTO<PermisosBodegaTB> consultarPermisosBodegaFiltros(
			RequestConsultarPermisosBodegaDTO request) {
		ResponseConsultarDTO<PermisosBodegaTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder(" SELECT p FROM PermisosBodegaTB p WHERE 1 = 1 ");
		// WHERE
		if (request.getPermisosBodega().getBodega() != null) {
			if (request.getPermisosBodega().getBodega().getId() != 0l) {
				JPQL.append(" AND p.bodega.id = :BODEGA ");
				pamametros.put("BODEGA", request.getPermisosBodega().getBodega().getId());
			}
		}
		if (request.getPermisosBodega().getUsuario() != null) {
			if (request.getPermisosBodega().getUsuario().getId() != 0l) {
				JPQL.append(" AND p.usuario.id = :USUARIO ");
				pamametros.put("USUARIO", request.getPermisosBodega().getUsuario().getId());
			}
		}

		String COUNT = "SELECT COUNT(p) " + JPQL.toString().substring(JPQL.toString().indexOf("FROM"));

		// Q. Order By
		JPQL.append(" ORDER BY p.id DESC");
		// END QUERY

		// QUERY COUNT
		TypedQuery<Long> queryCount = em.createQuery(COUNT, Long.class);
		pamametros.forEach((k, v) -> queryCount.setParameter(k, v));
		response.setRegistrosTotales(queryCount.getSingleResult());

		TypedQuery<PermisosBodegaTB> query = em.createQuery(JPQL.toString(), PermisosBodegaTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<PermisosBodegaTB> lista = query.getResultList();

		response.setResultado(lista);

		return response;
	}

	@Override
	public List<PermisosBodegaTB> consultarPermisosUsuarioBodega(RequestBuscarPermisosBodegaUsuarioDTO request) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT p FROM PermisosBodegaTB p WHERE 1 = 1 ");
		// WHERE

		JPQL.append(" AND p.bodega.id = :BODEGA ");
		pamameters.put("BODEGA", request.getIdBodega());

		JPQL.append(" AND p.usuario.id = :USUARIO ");
		pamameters.put("USUARIO", request.getIdUsuario());

		// Q. Order By
		JPQL.append(" ORDER BY p.id");
		// END QUERY

		TypedQuery<PermisosBodegaTB> query = em.createQuery(JPQL.toString(), PermisosBodegaTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}

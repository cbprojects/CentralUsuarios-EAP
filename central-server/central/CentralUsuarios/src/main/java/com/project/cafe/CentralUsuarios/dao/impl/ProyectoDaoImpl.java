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
import com.project.cafe.CentralUsuarios.dao.ICajaDao;
import com.project.cafe.CentralUsuarios.dao.IProyectoDao;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarCajasDTO;
import com.project.cafe.CentralUsuarios.dto.RequestConsultarProyectosDTO;
import com.project.cafe.CentralUsuarios.dto.ResponseConsultarDTO;
import com.project.cafe.CentralUsuarios.model.CajaTB;
import com.project.cafe.CentralUsuarios.model.ProyectoTB;
import com.project.cafe.CentralUsuarios.util.ConstantesValidaciones;

@Repository
public class ProyectoDaoImpl extends AbstractDao<ProyectoTB> implements IProyectoDao {

	@PersistenceContext(unitName = "default")
	private EntityManager em;

	@Override
	public ProyectoTB crearProyecto(ProyectoTB proyecto) {
		super.create(proyecto);
		return proyecto;
	}

	@Override
	public ProyectoTB modificarProyecto(ProyectoTB proyecto) {
		super.update(proyecto);
		return proyecto;
	}

	@Override
	public List<ProyectoTB> buscarProyectoPorNombreSociedad(String nombreProyecto, long id) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT p FROM ProyectoTB p WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(nombreProyecto)) {
			JPQL.append(" AND UPPER(p.nombre) = :NOMBRE ");
			pamameters.put("NOMBRE", nombreProyecto.toUpperCase());
		}
		if (id != 0) {
			JPQL.append(" AND p.sociedad.id = :SOCIEDAD ");
			pamameters.put("SOCIEDAD", id);
		}
		// Q. Order By
		JPQL.append(" ORDER BY p.id");
		// END QUERY

		TypedQuery<ProyectoTB> query = em.createQuery(JPQL.toString(), ProyectoTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

	@Override
	public ResponseConsultarDTO<ProyectoTB> consultarProyectosFiltros(RequestConsultarProyectosDTO request) {
		ResponseConsultarDTO<ProyectoTB> response = new ResponseConsultarDTO<>();

		// PARAMETROS
		Map<String, Object> pamametros = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder(" SELECT p FROM ProyectoTB p WHERE 1 = 1 ");
		// WHERE
		if (!StringUtils.isBlank(request.getProyecto().getNombre())) {
			JPQL.append(" AND UPPER(p.nombre) LIKE :NOMBRE ");
			pamametros.put("NOMBRE", ConstantesValidaciones.COMODIN_BD + request.getProyecto().getNombre().toUpperCase()
					+ConstantesValidaciones.COMODIN_BD );
		}
		if (request.getProyecto().getSociedad() != null) {
			if (request.getProyecto().getSociedad().getId() != 0l) {
				JPQL.append(" AND p.sociedad.id = :SOCIEDAD ");
				pamametros.put("SOCIEDAD", request.getProyecto().getSociedad().getId());
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

		TypedQuery<ProyectoTB> query = em.createQuery(JPQL.toString(), ProyectoTB.class);
		pamametros.forEach((k, v) -> query.setParameter(k, v));

		query.setFirstResult(request.getRegistroInicial());
		query.setMaxResults(request.getCantidadRegistro());
		List<ProyectoTB> lista = query.getResultList();

		response.setResultado(lista);

		return response;
	}

	@Override
	public List<ProyectoTB> consultarProyectosPorSociedad(Long id) {
		// PARAMETROS
		Map<String, Object> pamameters = new HashMap<>();

		// QUERY
		StringBuilder JPQL = new StringBuilder("SELECT p FROM ProyectoTB p WHERE 1 = 1 ");
		// WHERE
		if (id != 0) {
			JPQL.append(" AND p.sociedad.id = :SOCIEDAD ");
			pamameters.put("SOCIEDAD", id);
		}
		// Q. Order By
		JPQL.append(" ORDER BY p.id");
		// END QUERY

		TypedQuery<ProyectoTB> query = em.createQuery(JPQL.toString(), ProyectoTB.class);
		pamameters.forEach((k, v) -> query.setParameter(k, v));

		return query.getResultList();
	}

}
